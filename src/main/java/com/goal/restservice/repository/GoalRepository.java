package com.goal.restservice.repository;

import com.goal.restservice.domain.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Long> {

  List<Goal> findByUserIdOrderByModifiedDateDesc(long id);

  Goal findByTitle(String title);
}
