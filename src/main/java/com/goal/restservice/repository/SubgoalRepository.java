package com.goal.restservice.repository;

import com.goal.restservice.domain.Goal;
import com.goal.restservice.domain.Subgoal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubgoalRepository extends JpaRepository<Subgoal, Long> {
    List<Subgoal> findAllByGoalOrderById(Goal goal);
}
