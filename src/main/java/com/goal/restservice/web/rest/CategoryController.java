package com.goal.restservice.web.rest;

import com.goal.restservice.dto.CategoryDto;
import com.goal.restservice.dto.GoalDto;
import com.goal.restservice.service.CategoryServiceImpl;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/categories", method = {RequestMethod.GET, RequestMethod.POST})
public class CategoryController {

  private final CategoryServiceImpl categoryServiceImpl;

  public CategoryController(CategoryServiceImpl categoryServiceImpl) {
    this.categoryServiceImpl = categoryServiceImpl;
  }

  @PostMapping
  public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
    CategoryDto newCategory = categoryServiceImpl.createCategory(categoryDto);

    return newCategory == null ? new ResponseEntity<CategoryDto>(HttpStatus.INTERNAL_SERVER_ERROR)
        : new ResponseEntity<CategoryDto>(newCategory, HttpStatus.CREATED);
  }

  @GetMapping("/{name}")
  public ResponseEntity<List<GoalDto>> findGoalsByCategory(@PathVariable String name) {
    return new ResponseEntity<List<GoalDto>>(categoryServiceImpl.getGoalsByCategory(name),
        HttpStatus.FOUND);
  }
}
