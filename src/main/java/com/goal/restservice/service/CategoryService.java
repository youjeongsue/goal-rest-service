package com.goal.restservice.service;

import com.goal.restservice.dto.CategoryDto;
import java.util.List;

public interface CategoryService {

  CategoryDto createCategory(CategoryDto categoryDto);

  List<CategoryDto> findAll();

  CategoryDto getCategoryByName(String name);
}