package com.goal.restservice.repository;

import com.goal.restservice.domain.Goal;
import com.goal.restservice.domain.Note;
import com.goal.restservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByUserOrderByCreatedDate(User user);

    List<Note> findAllByUserAndGoalOrderByCreatedDate(User user, Goal goal);
}
