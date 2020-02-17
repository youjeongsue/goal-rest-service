package com.goal.restservice.web.rest;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goal.restservice.domain.categories.Category;
import com.goal.restservice.dto.GoalDto;

import com.goal.restservice.service.CategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(GoalController.class)
class GoalControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private CategoryServiceImpl categoryServiceImpl;
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void 목표를_생성한다() throws Exception {

    Category category = Category.builder().name("English").build();
    categoryServiceImpl.save(category);
    GoalDto goalDto = GoalDto.builder().title("TOEIC").desc("990")
        .category("English")
        .build();

    mockMvc
        .perform(MockMvcRequestBuilders.post("/api/goals").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(goalDto)))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void 목표를_읽는다() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.get("/api/goal/" + 1))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  public void 목표를_수정한다() throws Exception {
    String title = "영어 공부";
    String desc = "2020 토익!";
    GoalDto goal = GoalDto.builder().title(title).desc(desc).build();

//    given(goalServiceImpl.createGoal(any(GoalDto.class))).willReturn(goal);

  }

  @Test
  public void 목표를_지운다() throws Exception {
  }


}