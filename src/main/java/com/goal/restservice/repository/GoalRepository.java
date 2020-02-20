package com.goal.restservice.repository;

import com.goal.restservice.domain.Category;
import com.goal.restservice.domain.Goal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface GoalRepository extends JpaRepository<Goal, Long> {

  List<Goal> findByUserIdOrderByModifiedDateDesc(long id);
//  Optional<List<Goal>> findByUserIdOrderByModifiedDateDesc(long id);

//  @Modifying
//  @Query("update Goal g set g.category = ?1, g.title = ?2 where g.id = ?4")
//  Goal setGoalById(Category category, String title, String desc);
}
