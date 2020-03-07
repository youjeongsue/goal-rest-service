package com.goal.restservice.web.rest;

import com.goal.restservice.common.error.SubgoalCreateFailException;
import com.goal.restservice.common.error.SubgoalDoesNotExistException;
import com.goal.restservice.dto.SubgoalDto;
import com.goal.restservice.service.SubgoalServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subgoals")
public class SubgoalController {

  private final SubgoalServiceImpl subgoalServiceImpl;

  public SubgoalController(SubgoalServiceImpl subgoalServiceImpl) {
    this.subgoalServiceImpl = subgoalServiceImpl;
  }

  @PostMapping
  public ResponseEntity<SubgoalDto> createSubgoal(@RequestBody SubgoalDto subgoal) {
    SubgoalDto newSubgoal = subgoalServiceImpl.createSubgoal(subgoal);

    if (newSubgoal == null) {
      throw new SubgoalCreateFailException();
    }

    return new ResponseEntity<SubgoalDto>(newSubgoal, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<SubgoalDto> getSubgoalById(@PathVariable Long id) {
    SubgoalDto subgoal = subgoalServiceImpl.getSubgoalById(id);

    if (subgoal == null) {
      throw new SubgoalDoesNotExistException();
    }

    return new ResponseEntity<SubgoalDto>(subgoal, HttpStatus.OK);
  }


}
