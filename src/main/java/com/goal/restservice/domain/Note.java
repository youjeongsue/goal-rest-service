package com.goal.restservice.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Note extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Goal goal;

    //TODO: add join with "subgoal"

    @ManyToOne
    @JoinColumn
    private User user;

    @CreatedDate
    private LocalDateTime createdDate;

    private String contents;
    private Integer rating;

    @Builder
    public Note(User user, Goal goal, String contents, Integer rating){
        this.user = user;
        this.goal = goal;
        this.contents = contents;
        this.rating = rating;
    }

    //TODO: add "subgoal" to update
    public void update(String contents, Integer rating){
        if(contents != null)
            this.contents = contents;
        if(rating != null)
            this.rating = rating;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGoal(Goal goal){ this.goal = goal; }

    public void setUser(User user){ this.user = user; }
}
