package com.goal.restservice.service;

import com.goal.restservice.domain.Category;
import com.goal.restservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class CategoryServiceImpl {

  private final CategoryRepository categoryRepository;

  public void save(Category category) {
    categoryRepository.save(category);
  }

  public List<Category> findAll() {
    return categoryRepository.findAll();
  }

  public Category findByName(String name) {
    return categoryRepository.findByName(name);
  }
}
