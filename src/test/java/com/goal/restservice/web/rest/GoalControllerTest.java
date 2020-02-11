package com.goal.restservice.web.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goal.restservice.domain.goals.Goal;
import com.goal.restservice.repository.GoalRepository;
import com.goal.restservice.service.GoalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(GoalController.class)
class GoalControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GoalRepository goalRepository;

  @MockBean
  private GoalService goalService;

  @Test
  public void 목표를_생성한다() throws Exception {

    Goal goal = Goal.builder().title("TOEIC").desc("990").build();

    given(goalService.save(any(Goal.class))).willReturn(goal);

    ObjectMapper objectMapper = new ObjectMapper();

    mockMvc.perform(MockMvcRequestBuilders.post("/goal")
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding("utf-8")
        .content(objectMapper.writeValueAsString(goal)))
        .andExpect(status().isOk())
        .andExpect(content().string("success"))
        .andDo(print());
  }

  @Test
  public void 목표를_읽는다() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.get("/goal/" + 1))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  public void 목표를_수정한다() throws Exception {
    String title = "영어 공부";
    String desc = "2020 토익!";
    Goal goal = Goal.builder().title(title).desc(desc).build();

    given(goalService.save(any(Goal.class))).willReturn(goal);

  }

  @Test
  public void 목표를_지운다() throws Exception {
  }


}