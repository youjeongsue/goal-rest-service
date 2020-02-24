package com.goal.restservice.service;

import com.goal.restservice.dto.CategoryDto;
import com.goal.restservice.dto.GoalDto;
import java.util.List;

public interface CategoryService {

  CategoryDto createCategory(CategoryDto categoryDto);

  List<CategoryDto> findAll();

  CategoryDto getCategoryByName(String name);

  List<GoalDto> getGoalsByCategory(String category);
}