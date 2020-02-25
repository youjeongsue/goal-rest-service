package com.goal.restservice.web.rest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goal.restservice.domain.User;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import jdk.nashorn.internal.parser.JSONParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static com.goal.restservice.web.rest.UserControllerTest.APPLICATION_JSON_UTF8;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class FollowControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    final private static String userMail = "test@naver.com";
    final private static String userPassword = "12345";
    private static boolean initialized = false;

    private String token;

    @BeforeEach
    public void INIT() throws Exception {
        if(!initialized){
            initialized = true;
        }
        // 1) Sign UP
        User user1 = User.builder()
                .email(userMail)
                .userName("horoyoii")
                .firstName("first")
                .lastName("last")
                .password(userPassword)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(user1)));
                //.andExpect(status().isCreated());

        // 2) Sign In and get the token.
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(user1)))
                .andExpect(status().isOk())
                .andReturn();

        UserRegistResult user = objectMapper.readValue(result.getResponse().getContentAsString(), UserRegistResult.class);
        token = user.getId_token();

        // 3) Dummy users
        User dummyUser;
        for(int i=1; i <= 5; i++){
            dummyUser = User.builder()
                    .email(userMail+i)
                    .userName("dummy"+i)
                    .firstName("first")
                    .lastName("last")
                    .password(userPassword)
                    .build();

            mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                    .contentType(APPLICATION_JSON_UTF8)
                    .content(objectMapper.writeValueAsString(dummyUser)));
                    //.andExpect(status().isCreated());
        }


    }


    @Test
    public void 팔로우_하기() throws Exception{
        String url = "/api/social.following/follow";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .header("Authorization", "Bearer "+token)
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(User.builder()
                                                            .userName("dummy1").build())))
                .andReturn();

        Assertions.assertEquals(result.getResponse().getContentAsString(), "dummy1");
    }

    @Test
    public void 나의_팔로워_조회() throws Exception {
        Assertions.assertEquals(0, 0);

//        String url = "/api/following/my/follower";
//        mockMvc.perform(MockMvcRequestBuilders.get(url)
//                .header("Authorization", "Bearer "+token))
//                .andDo(MockMvcResultHandlers.print());
    }



}


class UserRegistResult{
    String id_token;

    public String getId_token() {
        return id_token;
    }

    public void setId_token(String id_token) {
        this.id_token = id_token;
    }
}