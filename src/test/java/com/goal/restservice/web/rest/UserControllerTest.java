package com.goal.restservice.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goal.restservice.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserControllerTest {

  public static final MediaType APPLICATION_JSON_UTF8 =
      new MediaType(
          APPLICATION_JSON.getType(), APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @Test
  public void 사용자를_생성한다() throws Exception {

    // mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")).andDo(print());
    // given
    User user =
        User.builder()
            .email("test@naver.com")
            .userName("jsom")
            .firstName("first")
            .lastName("last")
            .password("12345")
            //        .activated(false)
            .build();

    // when
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/api/users")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(user)))
        .andDo(MockMvcResultHandlers.print());

    // then

  }

  @Test
  public void 사용자생성_예외처리() throws Exception {

    // given
    User user =
        User.builder()
            .email("test@naver.com")
            .userName("json")
            .firstName("first")
            .lastName("last")
            .password("12345")
            //        .activated(false)
            .build();

    mockMvc.perform(
        MockMvcRequestBuilders.post("/api/users")
            .contentType(APPLICATION_JSON_UTF8)
            .content(objectMapper.writeValueAsString(user)));

    // when : 중복된 이메일 존재
    User dup_user =
        User.builder()
            .email("test@naver.com")
            .userName("json")
            .password("12345")
            //        .activated(false)
            .build();

    // then : 400 bad request
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/api/users")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(dup_user)))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @Test
  public void 사용자를_가져온다() throws Exception {

    // given
    User user =
        User.builder()
            .email("test@naver.com")
            .userName("jsom")
            .firstName("first")
            .lastName("last")
            .password("12345")
            //        .activated(false)
            .build();

    // when
    mockMvc.perform(
        MockMvcRequestBuilders.post("/api/users")
            .contentType(APPLICATION_JSON_UTF8)
            .content(objectMapper.writeValueAsString(user)));

    mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1")).andDo(print());
  }

  @Test
  public void 사용자없음_예외처리를한다() throws Exception {}
}
