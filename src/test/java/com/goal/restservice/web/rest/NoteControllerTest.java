package com.goal.restservice.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goal.restservice.domain.*;
import com.goal.restservice.dto.CategoryDto;
import com.goal.restservice.dto.NoteDto;
import com.goal.restservice.dto.SubgoalDto;
import com.goal.restservice.dto.UserDTO;
import com.goal.restservice.repository.CategoryRepository;
import com.goal.restservice.repository.GoalRepository;
import com.goal.restservice.repository.SubgoalRepository;
import com.goal.restservice.repository.UserRepository;
import com.goal.restservice.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class NoteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    GoalRepository goalRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SubgoalRepository subgoalRepository;

    @Autowired
    UserServiceImpl userService;
    @Autowired
    SubgoalServiceImpl subgoalService;
    @Autowired
    NoteServiceImpl noteService;

    @BeforeEach
    public void test_setting() throws Exception{
//        //create 2 subgoal
//        SubgoalDto subgoalDto1 = SubgoalDto.builder().title("1-1").desc("subgoal1-1").goalTitle("Test1").build();
//        subgoalService.createSubgoal(subgoalDto1);
//
//        SubgoalDto subgoalDto2 = SubgoalDto.builder().title("1-2").desc("subgoal1-2").goalTitle("Test1").build();
//        subgoalService.createSubgoal(subgoalDto2);
    }

    @Test
    public void createNote() throws Exception{
        Optional<User> optionalUser = userRepository.findById(1L);
        User user = optionalUser.get();
        Goal goal = goalRepository.findByTitle("Test1");
        List<Subgoal> subgoals = subgoalRepository.findAllByGoalOrderById(goal);
        Subgoal subgoal = subgoals.get(0); //1-1

        NoteDto noteDto = NoteDto.builder().user(user).goal(goal).subgoal(subgoal).contents("오늘은 1-1을 잘 했다").rating(4).build();
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(noteDto)))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void readNote() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/notes/"))
                .andDo(MockMvcResultHandlers.print());
    }

}
