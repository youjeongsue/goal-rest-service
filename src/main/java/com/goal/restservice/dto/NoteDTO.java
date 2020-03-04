package com.goal.restservice.dto;

import com.goal.restservice.domain.Goal;
import com.goal.restservice.domain.Note;
import com.goal.restservice.domain.User;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {
    private Long id;

    private Goal goal;

    private User user;

    private LocalDate createdDate;

    private String contents;

    private Integer rating;

    @Builder
    public NoteDTO(Long id, User user, Goal goal, String contents, Integer rating){
        this.id = id;
        this.user = user;
        this.goal = goal;
        this.contents = contents;
        this.rating = rating;
    }
}
