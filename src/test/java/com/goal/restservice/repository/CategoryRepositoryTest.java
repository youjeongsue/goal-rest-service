package com.goal.restservice.repository;

import com.goal.restservice.domain.Category;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CategoryRepositoryTest {

  @Autowired
  CategoryRepository categoryRepository;
  @Test
  public void 카테고리_생성하기() {
    //given
    categoryRepository.save(Category.builder()
        .name("category1")
        .build());

    System.out.println("#################################");

    //when
    List<Category> categoryList = categoryRepository.findAll();

    //then
    Category category = categoryList.get(0);
    System.out.println(category.getName());
    System.out.println(category.getGoals());

    Optional<Category> categoryOptional= categoryRepository.findById(category.getId());

    System.out.println(categoryOptional.get().getId());
  }


}