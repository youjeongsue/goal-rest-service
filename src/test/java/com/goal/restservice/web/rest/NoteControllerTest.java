package com.goal.restservice.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goal.restservice.domain.Goal;
import com.goal.restservice.dto.GoalDto;
import com.goal.restservice.dto.NoteDTO;
import com.goal.restservice.dto.UserDTO;
import com.goal.restservice.repository.NoteRepository;
import com.goal.restservice.service.NoteServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class NoteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private NoteServiceImpl noteServiceImpl;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private NoteRepository noteRepository;

    @Test
    public void create_note() throws Exception{
        NoteDTO noteDTO = new NoteDTO("test_contents", 1);

        noteServiceImpl.createNote(noteDTO);
    }

}
