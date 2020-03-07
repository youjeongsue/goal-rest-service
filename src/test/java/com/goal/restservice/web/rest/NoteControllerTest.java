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
        UserDTO userDTO = UserDTO.builder().email("test@naver.com").password("12345").username("jsom").firstName("first").lastName("last").build();

        Category category = Category.builder().name("English").build();
        CategoryDto categoryDto = CategoryDto.builder().name("English").build();
        Goal goal = Goal.builder().title("TOEIC").desc("990").category(category).build();
        GoalDto goalDto = GoalDto.builder().goal(goal).build();

        SubgoalDto subgoalDto = SubgoalDto.builder().title("subgoal").desc("my subgoal").goalTitle("TOEIC").build();

        Note note = Note.builder().build();

        System.out.println(">>>>>>>>>> note id : " + note.getId());
        //NoteDto noteDTO = NoteDto.builder()..build();
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/notes")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(noteDTO)))
//                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void createNote() throws Exception{
        //BeforeEach -> create a note
    }

}
