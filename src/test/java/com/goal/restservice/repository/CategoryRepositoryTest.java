package com.goal.restservice.repository;

import com.goal.restservice.domain.Category;
import com.goal.restservice.dto.CategoryDto;
import com.goal.restservice.service.CategoryServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CategoryRepositoryTest {

  @Autowired
  private CategoryRepository categoryRepository;

  @Test
  public void 카테고리_생성하기() {
    Category category = Category.builder()
        .name("category1")
        .build();
    categoryRepository.save(category);
    System.out.println(category);
  }

}