package com.goal.restservice.service;

import com.goal.restservice.common.error.GoalDoesNotExistException;
import com.goal.restservice.domain.Goal;
import com.goal.restservice.domain.Subgoal;
import com.goal.restservice.dto.SubgoalDto;
import com.goal.restservice.repository.GoalRepository;
import com.goal.restservice.repository.SubgoalRepository;
import com.goal.restservice.repository.UserRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SubgoalServiceImpl implements SubgoalService {

  private final GoalRepository goalRepository;
  private final UserRepository userRepository;
  private final SubgoalRepository subgoalRepository;

  public SubgoalServiceImpl(GoalRepository goalRepository,
      UserRepository userRepository,
      SubgoalRepository subgoalRepository) {
    this.goalRepository = goalRepository;
    this.userRepository = userRepository;
    this.subgoalRepository = subgoalRepository;
  }

  @Override
  public SubgoalDto createSubgoal(SubgoalDto subgoalDto) {
    // TODO: Find user by token ID?
    // User user = userRepository.getOne(subgoalDto.getUserId());
    Goal goal = goalRepository.findByTitle(subgoalDto.getGoalTitle());

    if (goal == null) {
      throw new GoalDoesNotExistException();
    }

    Subgoal subgoal = subgoalRepository
        .save(Subgoal.builder().goal(goal).title(subgoalDto.getTitle()).desc(subgoalDto.getDesc())
            .dueDate(subgoalDto.getDueDate()).build());

    goal.addSubGoal(subgoal);

    return SubgoalDto.builder().title(goal.getTitle()).desc(subgoal.getDesc())
        .goalTitle(subgoalDto.getGoalTitle()).dueDate(subgoal.getDueDate()).build();
  }

  @Override
  public List<SubgoalDto> getSubgoalsByGoalId(Long id) {
    return null;
  }

  @Override
  public SubgoalDto getSubgoalById(Long id) {

    return null;
  }

  @Override
  public SubgoalDto updateSubgoal(Long id, SubgoalDto goalDto) {
    return null;
  }

  @Override
  public boolean deleteSubgoal(Long id) {
    return false;
  }
}
