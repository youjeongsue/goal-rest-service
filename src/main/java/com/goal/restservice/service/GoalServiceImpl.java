package com.goal.restservice.service;

import com.goal.restservice.common.error.CategoryDoesNotExistException;
import com.goal.restservice.domain.Category;
import com.goal.restservice.domain.Goal;
import com.goal.restservice.domain.User;
import com.goal.restservice.dto.GoalDto;
import com.goal.restservice.repository.CategoryRepository;
import com.goal.restservice.repository.GoalRepository;
import com.goal.restservice.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GoalServiceImpl implements GoalService {

  private final GoalRepository goalRepository;
  private final UserRepository userRepository;
  private final CategoryRepository categoryRepository;
  private final JwtServiceImpl jwtServiceImpl;

  public GoalServiceImpl(GoalRepository goalRepository,
      CategoryServiceImpl categoryServiceImpl,
      UserRepository userRepository,
      CategoryRepository categoryRepository,
      JwtServiceImpl jwtServiceImpl) {
    this.goalRepository = goalRepository;
    this.userRepository = userRepository;
    this.categoryRepository = categoryRepository;
    this.jwtServiceImpl = jwtServiceImpl;
  }

  @Override
  public GoalDto createGoal(GoalDto goalDto) {

    Category category = categoryRepository.findByName(goalDto.getCategory());

    if (category == null) {
      throw new CategoryDoesNotExistException();
    }

    Goal goal = goalRepository
        .save(Goal.builder().category(categoryRepository.findByName(goalDto.getCategory()))
            .title(goalDto.getTitle()).user(userRepository.getOne(jwtServiceImpl.getUserId()))
            .desc(goalDto.getDesc()).dueDate(goalDto.getDueDate())
            .build());

    category.addGoal(goal);

    return GoalDto.builder().category(goalDto.getCategory()).title(goal.getTitle())
        .desc(goal.getDesc())
        .userId(jwtServiceImpl.getUserId())
        .build();
  }

  @Override
  public List<GoalDto> getGoalsByUserId(Long id) {
    List<Goal> goals = goalRepository.findByUserIdOrderByModifiedDateDesc(id);
//    Optional<List<Goal>> goals = goalRepository.findByUserIdOrderByModifiedDateDesc(id);
    List<GoalDto> goalDtos = new ArrayList<GoalDto>();
    for (Goal goal : goals) {
      if (goal != null) {
        goalDtos.add(
            GoalDto.builder().category(goal.getTitle()).title(goal.getTitle()).desc(goal.getDesc())
                .build());
      }
    }
    return goalDtos;
  }

  @Override
  public GoalDto getGoalById(Long id) {
    Optional<Goal> optionalGoal = goalRepository.findById(id);
    return optionalGoal.map(
        goal -> GoalDto.builder().category(goal.getCategory().getName()).title(goal.getTitle())
            .desc(goal.getDesc()).build()).orElse(null);
  }

  @Override
  public GoalDto updateGoal(Long id, GoalDto goalDto) {

    Goal goal = goalRepository.getOne(id);

    goal.updateGoal(categoryRepository.findByName(goalDto.getCategory()), goalDto.getTitle(),
        goalDto.getDesc(),
        goalDto.getDueDate());

    goal = goalRepository.save(goal);

    return GoalDto.builder().category(goal.getCategory().getName()).title(goal.getTitle())
        .desc(goal.getDesc())
        .build();

  }

  @Override
  public boolean deleteGoal(Long id) {
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
