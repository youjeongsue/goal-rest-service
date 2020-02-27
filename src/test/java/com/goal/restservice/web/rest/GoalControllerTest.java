package com.goal.restservice.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goal.restservice.repository.UserRepository;
import com.goal.restservice.service.CategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class GoalControllerTest {

  @Autowired private MockMvc mockMvc;
  @Autowired private CategoryServiceImpl categoryServiceImpl;
  @Autowired private ObjectMapper objectMapper;
  @Autowired private UserRepository userRepository;

  @Test
  public void 목표를_생성한다() throws Exception {
    // given
    //    Category category = Category.builder()
    //        .name("category1")
    //        .build();

    //    categoryServiceImpl.save(category);
    //    User user = User.builder().name("user").build();
    //    userRepository.save(user);
    //
    //    GoalDto goalDto = GoalDto.builder().title("TOEIC").desc("990")
    //        .userId(userRepository.findAll().get(0).getId())
    //        .category("English")
    //        .build();
    //
    //    mockMvc
    //
    // .perform(MockMvcRequestBuilders.post("/api/goals").contentType(MediaType.APPLICATION_JSON)
    //            .content(objectMapper.writeValueAsString(goalDto)))
    //        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void 목표를_읽는다() throws Exception {

    //    mockMvc.perform(MockMvcRequestBuilders.get("/api/goal/" + 1))
    //        .andExpect(status().isOk())
    //        .andDo(print());
  }

  @Test
  public void 목표를_수정한다() throws Exception {
    //    String title = "영어 공부";
    //    String desc = "2020 토익!";
    //    GoalDto goal = GoalDto.builder().title(title).desc(desc).build();

    //    given(goalServiceImpl.createGoal(any(GoalDto.class))).willReturn(goal);

  }

  @Test
  public void 목표를_지운다() throws Exception {}
}
