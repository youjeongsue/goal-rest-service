package com.goal.restservice.repository;

import com.goal.restservice.domain.User;
import com.goal.restservice.domain.Category;
import com.goal.restservice.domain.Goal;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class GoalRepositoryTest {

  @Autowired
  GoalRepository goalRepository;

  @Autowired
  CategoryRepository categoryRepository;
  @Autowired
  UserRepository userRepository;

  @BeforeEach
  public void 목표_생성하기() {

    Category category = Category.builder()
        .name("category1")
        .build();
    categoryRepository.save(category);

    User user = User.builder().userName("user").password("pass").introduction("intro")
        .email("email@hi.com").firstName("first")
        .lastName("last").imageUrl("url").build();
    user = userRepository.save(user);

    goalRepository
        .save(Goal.builder().user(user).category(category).title("title").desc("desc").build());


  }

  @Test
  public void 목표_수정하기() {
    Optional<Goal> goalOptional = goalRepository.findById(1L);

    Goal goal = goalOptional.get();

    Category category2 = Category.builder()
        .name("category2")
        .build();
    categoryRepository.save(category2);

    goal.updateGoal(category2, "update", "update desc", null);

    goal = goalRepository.getOne(goal.getId());

    System.out.println(goal);
  }
}

