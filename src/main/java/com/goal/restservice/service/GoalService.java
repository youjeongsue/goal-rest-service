package com.goal.restservice.service;

import com.goal.restservice.dto.GoalDto;
import java.util.List;

public interface GoalService {

  GoalDto createGoal(GoalDto goalDto);

  List<GoalDto> getGoalByUserId(long id);

  GoalDto getGoalById(long id);

  GoalDto updateGoal(long id, GoalDto goalDto);

  boolean deleteGoal(long id);
}
