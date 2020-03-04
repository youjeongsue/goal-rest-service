package com.goal.restservice.service;

import com.goal.restservice.domain.Category;
import com.goal.restservice.domain.Goal;
import com.goal.restservice.dto.GoalDto;
import com.goal.restservice.repository.GoalRepository;
import com.goal.restservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GoalServiceImpl implements GoalService {

  private final GoalRepository goalRepository;
  private final CategoryServiceImpl categoryServiceImpl;
  private final UserRepository userRepository;

  public GoalServiceImpl(
      GoalRepository goalRepository,
      CategoryServiceImpl categoryServiceImpl,
      UserRepository userRepository) {
    this.goalRepository = goalRepository;
    this.categoryServiceImpl = categoryServiceImpl;
    this.userRepository = userRepository;
  }

  @Override
  public GoalDto createGoal(GoalDto goalDto) {
    // TODO: Replace hardcoded value
    categoryServiceImpl.save(Category.builder().name("English").build());

    Goal goal =
        goalRepository.save(
            Goal.builder()
                .category(categoryServiceImpl.findByName(goalDto.getCategory()))
                .title(goalDto.getTitle())
                .user(userRepository.findById(1L).get())
                .desc(goalDto.getDesc())
                .build());

    return GoalDto.builder()
        .category(goal.getCategory().getName())
        .title(goal.getTitle())
        .desc(goal.getDesc())
        .userId(1L)
        .build();
  }

  @Override
  public List<GoalDto> getGoalByUserId(long id) {
    List<Goal> goals = goalRepository.findByUserIdOrderByModifiedDateDesc(id);
    //    Optional<List<Goal>> goals = goalRepository.findByUserIdOrderByModifiedDateDesc(id);
    List<GoalDto> goalsDto = new ArrayList<GoalDto>();
    for (Goal goal : goals) {
      if (goal != null) {
        goalsDto.add(
            GoalDto.builder()
                .category(goal.getTitle())
                .title(goal.getTitle())
                .desc(goal.getDesc())
                .build());
      }
    }
    return goalsDto;
  }

  @Override
  public GoalDto getGoalById(long id) {
    Optional<Goal> optionalGoal = goalRepository.findById(id);
    return optionalGoal
        .map(
            goal ->
                GoalDto.builder()
                    .category(goal.getCategory().getName())
                    .title(goal.getTitle())
                    .desc(goal.getDesc())
                    .build())
        .orElse(null);
  }

  @Override
  public GoalDto updateGoal(long id, GoalDto goalDto) {

    Optional<Goal> oldGoal = goalRepository.findById(id);

    if (oldGoal.isPresent()) {
      Goal goal = oldGoal.get();

      goal =
          goalRepository.save(
              Goal.builder()
                  .id(goal.getId())
                  .category(categoryServiceImpl.findByName(goal.getCategory().getName()))
                  .id(goal.getId())
                  .title(goal.getTitle())
                  .desc(goal.getDesc())
                  .build());
      return GoalDto.builder()
          .category(goal.getCategory().getName())
          .title(goal.getTitle())
          .desc(goal.getDesc())
          .build();
    } else {
      return null;
    }
  }

  @Override
  public boolean deleteGoal(long id) {
    Optional<Goal> goal = goalRepository.findById(id);

    // TODO: check if user id of the goal matches current user id
    if (goal.isPresent()) {
      goalRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }
}
