package com.goal.restservice.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goal.restservice.dto.CategoryDto;
import com.goal.restservice.dto.GoalDto;
import com.goal.restservice.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class GoalControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  public void 목표를_생성한다() throws Exception {
    CategoryDto categoryDto = CategoryDto.builder()
        .name("English")
        .build();

    mockMvc.perform(
        MockMvcRequestBuilders.post("/api/categories").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(categoryDto)))
        .andDo(MockMvcResultHandlers.print());

    UserDTO userDTO = UserDTO.builder().userName("name").email("email@a.com").firstName("first")
        .lastName("last").imageUrl("i.com").introduction("hi").password("pass").build();

    mockMvc.perform(
        MockMvcRequestBuilders.post("/api/users").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userDTO)));

    GoalDto goalDto = GoalDto.builder().title("TOEIC").desc("990")
        .userId(1L).category("English").build();

    mockMvc
        .perform(MockMvcRequestBuilders.post("/api/goals").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(goalDto)))
        .andDo(MockMvcResultHandlers.print());

    GoalDto goalDto2 = GoalDto.builder().title("TOEFL").desc("990")
        .userId(1L).category("English").build();

    mockMvc
        .perform(MockMvcRequestBuilders.post("/api/goals").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(goalDto2)))
        .andDo(MockMvcResultHandlers.print());
    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/categories/English"))
            .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void 목표를_읽는다() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.get("/api/goals/" + 1))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void 목표를_수정한다() throws Exception {

  }

  @Test
  public void 목표를_지운다() throws Exception {
  }
}