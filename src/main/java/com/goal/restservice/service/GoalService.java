package com.goal.restservice.service;

import com.goal.restservice.domain.Goal;
import com.goal.restservice.domain.User;
import com.goal.restservice.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GoalService {

  private final GoalRepository goalRepository;

  public Goal findOne(Long id) {
    return goalRepository.getOne(id);
  }

  public Goal save(Goal goal) {
    return goalRepository.save(goal);
  }
}
