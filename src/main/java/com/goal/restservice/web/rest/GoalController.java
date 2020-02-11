package com.goal.restservice.web.rest;

import com.goal.restservice.domain.ResponseVO;
import com.goal.restservice.domain.goals.Goal;
import com.goal.restservice.repository.GoalRepository;
import com.goal.restservice.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goal")
@RequiredArgsConstructor
public class GoalController {

  private final GoalService goalService;
  private final GoalRepository goalRepository;

  @PostMapping
  public String create(@RequestBody Goal goal) {
    try {
      goalService.save(goal);
      return "success";
    } catch (Exception e) {
      return "failed";
    }
  }

  @GetMapping("/{id}")
  public Goal getDiary(@PathVariable long id) {
    return Goal.builder().id(id).build();
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable long id) {
    try {
      goalRepository.deleteById(id);
      return "success";
    } catch (Exception e) {
      return "failed";
    }
  }
}
