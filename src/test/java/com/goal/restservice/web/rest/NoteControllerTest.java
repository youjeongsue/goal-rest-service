package com.goal.restservice.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goal.restservice.domain.Category;
import com.goal.restservice.domain.Goal;
import com.goal.restservice.domain.Note;
import com.goal.restservice.domain.User;
import com.goal.restservice.dto.GoalDto;
import com.goal.restservice.dto.NoteDTO;
import com.goal.restservice.dto.UserDTO;
import com.goal.restservice.repository.NoteRepository;
import com.goal.restservice.service.GoalServiceImpl;
import com.goal.restservice.service.NoteServiceImpl;
import com.goal.restservice.service.UserServiceImpl;
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

    @Test
    public void createNote() throws Exception{
        User test_user = User.builder()
                .email("test@naver.com")
                .userName("jsom")
                .firstName("first")
                .lastName("last")
                .password("12345")
                .build();
        Category test_category = Category.builder().name("English").build();
        Goal test_goal = Goal.builder().title("TOEIC").desc("990").category(test_category).build();

        Note note = new Note(test_user, test_goal,"test_contents", 1);

        NoteDTO noteDTO = NoteDTO.builder().id(note.getId()).user(note.getUser()).goal(note.getGoal())
                .contents(note.getContents()).rating(note.getRating()).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/notes"))
                .andDo(MockMvcResultHandlers.print());
    }

}
