package com.goal.restservice.dto;

import com.goal.restservice.domain.Goal;
import com.goal.restservice.domain.Subgoal;
import com.goal.restservice.domain.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoteDto {
    private Long id;

    private User user;

    private Goal goal;

    private Subgoal subgoal;

    private LocalDateTime createdDate;

    private String contents;

    private Integer rating;

    @Builder
    public NoteDto(Long id, User user, Goal goal, Subgoal subgoal, String contents, Integer rating){
        this.id = id;
        this.user = user;
        this.goal = goal;
        this.subgoal = subgoal;
        this.contents = contents;
        this.rating = rating;
    }
}
