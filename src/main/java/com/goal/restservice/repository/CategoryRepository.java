package com.goal.restservice.repository;

import com.goal.restservice.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

  Category findByName(String name);
}
