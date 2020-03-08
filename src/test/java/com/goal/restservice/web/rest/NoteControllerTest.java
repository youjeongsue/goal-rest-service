package com.goal.restservice.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goal.restservice.domain.Category;
import com.goal.restservice.domain.Goal;
import com.goal.restservice.domain.Subgoal;
import com.goal.restservice.domain.User;
import com.goal.restservice.dto.CategoryDto;
import com.goal.restservice.dto.SubgoalDto;
import com.goal.restservice.dto.UserDTO;
import com.goal.restservice.repository.CategoryRepository;
import com.goal.restservice.repository.GoalRepository;
import com.goal.restservice.repository.SubgoalRepository;
import com.goal.restservice.repository.UserRepository;
import com.goal.restservice.service.CategoryService;
import com.goal.restservice.service.CategoryServiceImpl;
import com.goal.restservice.service.SubgoalServiceImpl;
import com.goal.restservice.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;


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
        Category category = categoryRepository.findByName("English");
        User user = userRepository.getOne(1L);
        Goal goal = goalRepository.findByTitle("Test1");
        List<Subgoal> subgoals = subgoalRepository.findAllByGoalOrderById(goal);
        for(Subgoal subgoal : subgoals){
            System.out.println(subgoal.getTitle());
        }
    }

    @Test
    public void readNote() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/notes/"))
                .andDo(MockMvcResultHandlers.print());
    }

}
