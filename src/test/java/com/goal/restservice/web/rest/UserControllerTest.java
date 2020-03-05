package com.goal.restservice.web.rest;

import com.goal.restservice.domain.User;
import com.goal.restservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @Test
  public void 사용자를_생성한다() throws Exception {
    String testName = "test_name";
    given(userService.save(any(User.class))).willReturn(User.builder().name(testName).build());

    mockMvc.perform(MockMvcRequestBuilders.post("/test/" + testName))
        .andExpect(status().isOk())
        .andExpect(jsonPath("name").value(testName));
  }

  @Test
  public void 사용자를_가져온다() throws Exception {

  }

  @Test
  public void 사용자없음_예외처리를한다() throws Exception {

  }
}