package com.goal.restservice.repository;

import com.goal.restservice.domain.goals.Goal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {

  List<Goal> findByUserIdOrderByModifiedDateDesc(long id);
}
