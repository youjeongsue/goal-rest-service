package com.goal.restservice.dto;

import com.goal.restservice.domain.Goal;
import com.goal.restservice.domain.Note;
import com.goal.restservice.domain.Subgoal;
import com.goal.restservice.domain.User;
import jdk.internal.jline.internal.TestAccessible;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoteDto {
    private User user;

    private Goal goal;

    private Subgoal subgoal;

    private LocalDateTime createdDate;

    private String contents;

    private Integer rating;

    @Builder
    public NoteDto(User user, Goal goal, Subgoal subgoal, String contents, Integer rating){
        this.user = user;
        this.goal = goal;
        this.subgoal = subgoal;
        this.contents = contents;
        this.rating = rating;
    }

    public Note noteDtoToNote(){
        return  Note.builder()
                .user(this.getUser())
                .goal(this.getGoal())
                .subgoal(this.getSubgoal())
                .contents(this.getContents())
                .rating(this.getRating())
                .build();
    }
}
