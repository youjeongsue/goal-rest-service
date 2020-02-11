package com.goal.restservice.repository;

import com.goal.restservice.domain.goals.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {

}
