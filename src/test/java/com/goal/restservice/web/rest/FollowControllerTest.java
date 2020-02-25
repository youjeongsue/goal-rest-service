package com.goal.restservice.web.rest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goal.restservice.domain.User;
import com.goal.restservice.dto.UserDTO;
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
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

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

    private static final String userMail = "dummy@naver.com";
    private static final String username ="dummy";
    private static final String userPassword = "12345";

    private static final String defaultURL = "/api/social.following";

    private static final int USERNUM = 3;

    private static boolean initialized = false;

    private String token;
    private User mainUser;

    @BeforeEach
    public void INIT() throws Exception {
        if(!initialized){
            initialized = true;
        }

        // 1) Sign UP
        // make 100 users
        for (int i = 0; i <= USERNUM; i++) {
          User dummyUser =
              User.builder()
                  .email(userMail+i)
                  .userName(username+i)
                  .firstName("first")
                  .lastName("last")
                  .password(userPassword)
                  .build();

          mockMvc.perform(
              MockMvcRequestBuilders.post("/api/users")
                  .contentType(APPLICATION_JSON_UTF8)
                  .content(objectMapper.writeValueAsString(dummyUser)))
                  .andExpect(status().isCreated());
        }

        mainUser = User.builder()
                .email(userMail+0)
                .password(userPassword)
                .build();

        // 2) Sign In and get the token.
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(mainUser)))
                .andExpect(status().isOk())
                .andReturn();

        UserRegistResult user = objectMapper.readValue(result.getResponse().getContentAsString(), UserRegistResult.class);
        token = user.getId_token();

    }

    @Test
    public void 내가_팔로워중인_유저_확인() throws Exception{
        String url = defaultURL+"/my/followed";

        // given
        팔로우_하기();

        // when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .header("Authorization", "Bearer "+token)).andExpect(status().isOk()).andReturn();

        // then
        UserDtoResult[] followedList = objectMapper.readValue(result.getResponse().getContentAsString(), UserDtoResult[].class);

        Assertions.assertEquals(3, followedList.length);

//        for(int i=1; i <= USERNUM; i++){
//            System.out.println("############");
//            System.out.println(followedList[0].getUserDTO());
//            UserDTO actualUser = followedList[i-1].getUserDTO();
//            Assertions.assertEquals(username+i, actualUser.getUserName());
//        }
    }

    //@Test
    public void 팔로우_하기() throws Exception{
        String url = defaultURL+"/follow";

        for (int i = 1; i <= USERNUM; i++) {
          MvcResult result =
              mockMvc
                  .perform(
                      MockMvcRequestBuilders.post(url)
                          .header("Authorization", "Bearer " + token)
                          .contentType(APPLICATION_JSON_UTF8)
                          .content(
                              objectMapper.writeValueAsString(
                                  User.builder().userName("dummy"+i).build()))).andExpect(status().isCreated())
                  .andReturn();
        }
    }



    @Test
    public void 나의_팔로워_조회() throws Exception {
        Assertions.assertEquals(0, 0);


    }



}

class UserDtoResult{
    UserDTO userDTO;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
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