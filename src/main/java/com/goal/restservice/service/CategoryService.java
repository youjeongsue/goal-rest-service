package com.goal.restservice.service;

import com.goal.restservice.domain.categories.Category;
import com.goal.restservice.repository.CategoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public interface CategoryService {


  void save(Category category);

  List<Category> findAll();

  Category findByName(String name);
}