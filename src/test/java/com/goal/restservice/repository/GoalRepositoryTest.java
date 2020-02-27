package com.goal.restservice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class GoalRepositoryTest {

  @Autowired GoalRepository goalRepository;

  @Autowired CategoryRepository categoryRepository;
  @Autowired UserRepository userRepository;

  @Test
  public void 목표_생성하기() {
    // given
    //    Category category = Category.builder()
    //        .name("category1")
    //        .build();
    //    categoryRepository.save(category);
    //
    //    User user = User.builder().name("user").build();
    //    userRepository.save(user);
    //
    //    goalRepository
    //
    // .save(Goal.builder().user(user).category(category).title("title").desc("desc").build());
    //
    //    //when
    //    List<Goal> goalList = goalRepository.findAll();
    //
    //    //then
    //    Goal goal = goalList.get(0);
    //    System.out.println(goal.getTitle());
    //    System.out.println(goal.getCategory().getName());
    //    System.out.println(category.getGoals().get(0).getTitle());
    //
    //    Optional<Goal> goalOptional = goalRepository.findById(goal.getId());
    //
    //    System.out.println(goalOptional.get().getId());
  }
}
