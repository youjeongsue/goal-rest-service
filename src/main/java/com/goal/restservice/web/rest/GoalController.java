package com.goal.restservice.web.rest;

import com.goal.restservice.domain.ResponseVO;
import com.goal.restservice.domain.User;
import com.goal.restservice.domain.goals.Goal;
import com.goal.restservice.dto.GoalDto;
import com.goal.restservice.repository.CategoryRepository;
import com.goal.restservice.repository.GoalRepository;
import com.goal.restservice.repository.UserRepository;
import com.goal.restservice.service.GoalService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goal")
@RequiredArgsConstructor
public class GoalController {

  private final GoalService goalService;
  private final GoalRepository goalRepository;
  private final CategoryRepository categoryRepository;
  private final UserRepository userRepository;

  @PostMapping
  public String create(@RequestBody GoalDto goal) {
    try {
//      long loginUserId = 1L;
//      Optional<Users> loginUsers = usersRepository.findById(loginUserId);
      goalRepository
          .save(Goal.builder().category(categoryRepository.findByName(goal.getCategory()))
              .title(goal.getTitle()).desc(goal.getDesc()).build());
      return "success";
    } catch (Exception e) {
      return "failed";
    }
  }

  @GetMapping("/{id}")
  public List<Goal> getGoalByUserId(@PathVariable long id) {
    List<Goal> goals = goalRepository.findByUserIdOrderByModifiedDateDesc(id);
    return goals;
  }

  @GetMapping("/{id}")
  public ResponseVO<?> getGoal(@PathVariable long id) {
    ResponseVO<Goal> resp = new ResponseVO<>();

    Optional<Goal> diary = goalRepository.findById(id);

    resp.setResponse(diary.orElse(null));
    return resp;
  }

  @PutMapping("/{id}")
  public String updateGoal(@PathVariable long id, @RequestBody GoalDto goal) {
    try {
//      long loginUserId = jwtService.getMemberId();
//      Optional<User> loginUsers = userRepository.findById(loginUserId);
      Optional<Goal> pastGoal = goalRepository.findById(id);

      if (!pastGoal.isPresent()) {
        return "failed";
      }
      goalRepository
          .save(Goal.builder().category(categoryRepository.findByName(goal.getCategory()))
              .id(id).title(goal.getTitle()).desc(goal.getDesc()).build());

      return "success";
    } catch (Exception e) {
      return "falied";
    }
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable long id) {
    try {
//      long loginUserId = jwtService.getMemberId();
//      Optional<User> loginUsers = userRepository.findById(loginUserId);
      Optional<Goal> goal = goalRepository.findById(id);
//      if (!goal.isPresent() || goal.get().getUser().getId() != loginUsers.get().getId())
//        return "failed";
      goalRepository.deleteById(id);

      return "success";
    } catch (Exception e) {
      return "falied";
    }
  }
}
