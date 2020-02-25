package com.goal.restservice.web.rest;

import com.goal.restservice.common.error.GoalCreateFailException;
import com.goal.restservice.common.error.GoalDoesNotExistException;
import com.goal.restservice.dto.GoalDto;
import com.goal.restservice.service.GoalServiceImpl;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(value = "/api/goals", method = {RequestMethod.GET, RequestMethod.POST,
//    RequestMethod.PATCH, RequestMethod.DELETE})
@RequestMapping("/api/goals")
public class GoalController {

  private final GoalServiceImpl goalServiceImpl;

  public GoalController(GoalServiceImpl goalServiceImpl) {
    this.goalServiceImpl = goalServiceImpl;
  }

  @PostMapping
  public ResponseEntity<GoalDto> createGoal(@RequestBody GoalDto goal) {
    GoalDto newGoal = goalServiceImpl.createGoal(goal);

    if (newGoal == null) {
      throw new GoalCreateFailException();
    }
    return new ResponseEntity<GoalDto>(newGoal, HttpStatus.CREATED);
  }

  @GetMapping("/profiles/{userId}")
  public List<GoalDto> getGoalByUserId(@PathVariable long userId) {
    return goalServiceImpl.getGoalsByUserId(userId);
  }

  @GetMapping("/{id}")
  public ResponseEntity<GoalDto> getGoalById(@PathVariable long id) {
    GoalDto goal = goalServiceImpl.getGoalById(id);

    if (goal == null) {
      throw new GoalDoesNotExistException();
    }

    return new ResponseEntity<GoalDto>(goal, HttpStatus.OK);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<GoalDto> updateGoal(@PathVariable long id, @RequestBody GoalDto goalDto) {
    GoalDto goal = goalServiceImpl.updateGoal(id, goalDto);

    return goal == null ? new ResponseEntity<GoalDto>(HttpStatus.INTERNAL_SERVER_ERROR)
        : new ResponseEntity<GoalDto>(goal, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteGoal(@PathVariable long id) {
    return goalServiceImpl.deleteGoal(id) ? new ResponseEntity<>(HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
