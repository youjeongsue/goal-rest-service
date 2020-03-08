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
        CategoryDto categoryDto = CategoryDto.builder().name("English").build();

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/categories").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDto)))
                .andDo(MockMvcResultHandlers.print());

        UserDTO userDTO = UserDTO.builder().username("name").email("email@a.com").firstName("first")
                .lastName("last").imageUrl("i.com").introduction("hi").password("pass").build();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)));

        GoalDto goalDto = GoalDto.builder().title("TOEIC").desc("990")
                .userId(1L).category("English").build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/goals")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(goalDto)))
                .andDo(MockMvcResultHandlers.print());

        NoteDto noteDto = NoteDto.builder().build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/notes")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(noteDto)))
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void createNote() throws Exception{
        //BeforeEach -> create a note
    }

}
