package com.goal.restservice.service;

import com.goal.restservice.common.error.CategoryDoesNotExistException;
import com.goal.restservice.domain.Category;
import com.goal.restservice.domain.Goal;
import com.goal.restservice.dto.GoalDto;
import com.goal.restservice.repository.CategoryRepository;
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
  private final UserRepository userRepository;
  private final CategoryRepository categoryRepository;
  private final JwtServiceImpl jwtServiceImpl;

  public GoalServiceImpl(
      GoalRepository goalRepository,
      UserRepository userRepository,
      CategoryRepository categoryRepository,
      JwtServiceImpl jwtServiceImpl) {
    this.goalRepository = goalRepository;
    this.userRepository = userRepository;
    this.categoryRepository = categoryRepository;
    this.jwtServiceImpl = jwtServiceImpl;
  }

  @Override
  public String createGoal(GoalDto goalDto) {

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

    return "success";
  }

  @Override
  public List<GoalDto> getMyGoals(Long id) {
    List<Goal> goals = goalRepository.findByUserIdOrderByModifiedDateDesc(id);
    List<GoalDto> goalDtos = new ArrayList<GoalDto>();

    for (Goal goal : goals) {
      if (goal != null) {
        goalDtos.add(new GoalDto(goal));
      }
    }
    return goalDtos;
  }

  @Override
  public GoalDto getGoalById(Long id) {
    Optional<Goal> optionalGoal = goalRepository.findById(id);
    return optionalGoal
        .map(GoalDto::new)
        .orElse(null);
  }

  @Override
  public GoalDto updateGoal(Long id, GoalDto goalDto) {

    Goal goal = goalRepository.getOne(id);

    goal.updateGoal(categoryRepository.findByName(goalDto.getCategory()), goalDto.getTitle(),
        goalDto.getDesc(),
        goalDto.getDueDate());

    goal = goalRepository.save(goal);

    return new GoalDto(goal);

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
