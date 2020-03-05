package com.goal.restservice.repository;

import com.goal.restservice.domain.Category;
import com.goal.restservice.domain.Goal;
import com.goal.restservice.domain.Subgoal;
import com.goal.restservice.domain.User;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class SubgoalRepositoryTest {

  @Autowired
  UserRepository userRepository;

  @Autowired
  SubgoalRepository subgoalRepository;

  @Autowired
  CategoryRepository categoryRepository;
  @Autowired
  GoalRepository goalRepository;

  Category category;
  Goal goal;

  @BeforeEach
  public void 목표_생성하기() {
    category = Category.builder()
        .name("category1")
        .build();
    categoryRepository.save(category);

    User user = User.builder().userName("user").password("pass").introduction("intro")
        .email("email@hi.com").firstName("first")
        .lastName("last").imageUrl("url").build();
    user = userRepository.save(user);

    goalRepository
        .save(Goal.builder().user(user).category(category).title("title1").desc("desc1").build());

    goalRepository
        .save(Goal.builder().user(user).category(category).title("title2").desc("desc2").build());
  }

  @Test
  public void 서브목표_생성하기() {
    Optional<Goal> goalOptional = goalRepository.findById(1L);
    goal = goalOptional.get();
    Subgoal subgoal = Subgoal.builder().goal(goal).desc("desc").title("title").dueDate(null)
        .build();
    subgoalRepository.save(subgoal);
    System.out.println(subgoal);
  }

  @AfterEach
  public void 객체_확인하기() {
    System.out.println(category);
    System.out.println(goal);
  }
}