package com.goal.restservice.service;

import com.goal.restservice.dto.GoalDto;
import java.util.List;

public interface GoalService {

  GoalDto createGoal(GoalDto goalDto);

  List<GoalDto> getGoalsByUserId(Long id);

  GoalDto getGoalById(Long id);

  GoalDto updateGoal(Long id, GoalDto goalDto);

  boolean deleteGoal(Long id);
}
