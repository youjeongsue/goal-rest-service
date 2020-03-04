package com.goal.restservice.service;

import com.goal.restservice.dto.GoalDto;
import java.util.List;

public interface GoalService {

  String createGoal(GoalDto goalDto);

  List<GoalDto> getMyGoals(Long id);

  GoalDto getGoalById(Long id);

  GoalDto updateGoal(Long id, GoalDto goalDto);

  boolean deleteGoal(Long id);
}
