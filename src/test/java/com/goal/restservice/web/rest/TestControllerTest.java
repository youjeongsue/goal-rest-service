package com.goal.restservice.web.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class TestControllerTest {

  @Autowired private MockMvc mockMvc;

  @Test
  public void 사용자를_생성한다() throws Exception {

    // given

    // when
    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/test/error"))
        .andDo(MockMvcResultHandlers.print());

    // then

  }
}
