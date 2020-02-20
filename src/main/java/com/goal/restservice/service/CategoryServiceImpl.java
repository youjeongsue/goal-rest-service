package com.goal.restservice.service;

import com.goal.restservice.domain.Category;
import com.goal.restservice.dto.CategoryDto;
import com.goal.restservice.repository.CategoryRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  public CategoryServiceImpl(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public CategoryDto createCategory(CategoryDto categoryDto) {

    Category category = categoryRepository
        .save(Category.builder().name(categoryDto.getName()).build());

    return CategoryDto.builder().name(category.getName()).build();
  }

  public List<CategoryDto> findAll() {
    List<Category> categories = categoryRepository.findAll();
    List<CategoryDto> categoryDtos = new ArrayList<>();
    for (Category c : categories) {
      if (c != null) {
        categoryDtos.add(CategoryDto.builder().name(c.getName()).build());
      }
    }
    return categoryDtos;
  }

  public CategoryDto getCategoryByName(String name) {
    Category category = categoryRepository.findByName(name);
    return CategoryDto.builder().name(category.getName()).build();
  }
}
