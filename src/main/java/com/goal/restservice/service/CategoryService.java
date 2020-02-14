package com.goal.restservice.service;

import com.goal.restservice.domain.categories.Category;
import com.goal.restservice.repository.CategoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public Category save(Category category) {
    return categoryRepository.save(category);
  }

  public List<Category> findAll() {
    return categoryRepository.findAll();
  }

  public Category findByName(String name) {
    return categoryRepository.findByName(name);
  }
}