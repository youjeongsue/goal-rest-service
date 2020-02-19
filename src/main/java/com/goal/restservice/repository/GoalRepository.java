package com.goal.restservice.repository;

import com.goal.restservice.domain.goals.Goal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
public interface GoalRepository extends JpaRepository<Goal, Long> {

  List<Goal> findByUserIdOrderByModifiedDateDesc(long id);
//  Optional<List<Goal>> findByUserIdOrderByModifiedDateDesc(long id);
}
||||||| e3eb698
public interface GoalRepository extends JpaRepository<Goal, Long> {

}
=======
public interface GoalRepository extends JpaRepository<Goal, Long> {}
>>>>>>> origin/develop
