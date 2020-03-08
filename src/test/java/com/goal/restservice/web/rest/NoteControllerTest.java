package com.goal.restservice.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goal.restservice.domain.*;
import com.goal.restservice.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class NoteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void test_setting() throws Exception{
        //create category
        Category category = Category.builder().name("English").build();
        CategoryDto categoryDto = CategoryDto.builder().name("English").build();
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/categories").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDto)))
                .andDo(MockMvcResultHandlers.print());

        //create user
        User user = User.builder().username("name").email("email@a.com").firstName("first")
                .lastName("last").imageUrl("i.com").introduction("hi").password("pass").build();
        UserDTO userDTO = UserDTO.ByFollowerBuilder().user(user).build();
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/users").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andDo(MockMvcResultHandlers.print());

        //create goal
        Goal goal = Goal.builder().user(user).category(category).title("영어 공부하기").build();
        GoalDto goalDto = GoalDto.builder().goal(goal).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/goals")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(goalDto)))
                .andDo(MockMvcResultHandlers.print());

//        //create note
//        NoteDto noteDto = NoteDto.builder().build();
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/notes")
//                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(noteDto)))
//                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void createNote() throws Exception{
        //BeforeEach -> create a note
    }

    @Test
    public void readNote() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/notes/"))
    }

}
