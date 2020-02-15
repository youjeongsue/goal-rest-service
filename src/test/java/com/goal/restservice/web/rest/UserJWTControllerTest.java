package com.goal.restservice.web.rest;

import com.goal.restservice.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class UserJWTControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void 로그인() throws Exception {

        User user = User.builder()
                .email("test@naver.com")
                .name("jsom")
                .password("12345")
                .activated(false)
                .build();
//
//        // when
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
//                .contentType(APPLICATION_JSON_UTF8)
//                .content(objectMapper.writeValueAsString(user)))
//                .andDo(MockMvcResultHandlers.print());

        // given
        mockMvc.perform(MockMvcRequestBuilders.post("/api/authenticate")
        .contentType(APPLICATION_JSON_UTF8));

        // when


        // then

    }

    @Test
    public void 로그아웃() throws Exception {


        // given


        // when


        // then

    }
}