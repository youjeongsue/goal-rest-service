package com.goal.restservice.service;

import com.goal.restservice.domain.goals.Goal;
import com.goal.restservice.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GoalService {

  private final GoalRepository goalRepository;

  public Goal save(Goal user) {
    return goalRepository.save(user);
  }
}
