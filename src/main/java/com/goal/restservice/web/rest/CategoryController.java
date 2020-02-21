package com.goal.restservice.web.rest;

import com.goal.restservice.dto.CategoryDto;
import com.goal.restservice.service.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

  private final CategoryServiceImpl categoryServiceImpl;

  @PostMapping
  public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
    CategoryDto newCategory = categoryServiceImpl.createCategory(categoryDto);

    return newCategory == null ? new ResponseEntity<CategoryDto>(HttpStatus.INTERNAL_SERVER_ERROR)
        : new ResponseEntity<CategoryDto>(newCategory, HttpStatus.CREATED);
  }
}
