package com.goal.restservice.web.rest;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goal.restservice.dto.CategoryDto;
import org.junit.jupiter.api.BeforeEach;
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
class CategoryControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void 카테고리를_생성한다() throws Exception {
    CategoryDto categoryDto = CategoryDto.builder()
        .name("category1")
        .build();

    mockMvc.perform(
        MockMvcRequestBuilders.post("/api/categories").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(categoryDto)))
        .andDo(MockMvcResultHandlers.print());
  }
}