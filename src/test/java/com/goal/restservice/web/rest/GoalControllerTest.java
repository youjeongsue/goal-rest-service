package com.goal.restservice.web.rest;

import com.goal.restservice.service.GoalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(GoalController.class)
class GoalControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GoalService goalService;

  @Test
  public void 목표를_생성한다() throws Exception {
  }

  @Test
  public void 목표를_수정한다() throws Exception {
  }

  @Test
  public void 목표를_읽는다() throws Exception {
        /*
            category, contents, id, d-day
         */
  }

  @Test
  public void 목표를_지운다() throws Exception {
  }


}