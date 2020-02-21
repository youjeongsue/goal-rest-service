package com.goal.restservice.service;

import com.goal.restservice.domain.Category;
import java.util.List;

public interface CategoryService {


  void save(Category category);

  List<Category> findAll();

  Category findByName(String name);
}