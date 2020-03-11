package com.goal.restservice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.goal.restservice.dto.NoteDto;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Note extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference(value = "note_user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JsonBackReference(value = "note_goal")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "goal_id")
    private Goal goal;

    @ManyToOne
    @JsonBackReference(value = "note_subgoal")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "subgoal_id")
    private Subgoal subgoal;

    @NotNull
    private String contents;
    @NotNull
    private Integer rating;

    @Builder
    public Note(User user, Goal goal, Subgoal subgoal, String contents, Integer rating){
        this.user = user;
        this.goal = goal;
        this.subgoal = subgoal;
        this.contents = contents;
        this.rating = rating;
    }

    public void setId(Long id) { this.id = id; }

    public void setUser(User user){ this.user = user; }

    public void setGoal(Goal goal){ this.goal = goal; }

    public void setSubgoal(Subgoal subgoal){ this.subgoal = subgoal; }

    public void update(Subgoal subgoal, String contents, Integer rating){
        if(subgoal != null){
            this.subgoal = subgoal;
        }
        if(contents != null) {
            this.contents = contents;
        }
        if(rating != null) {
            this.rating = rating;
        }
    }

    public NoteDto noteToNoteDto(){
        return NoteDto.builder()
                .user(this.getUser())
                .goal(this.getGoal())
                .subgoal(this.getSubgoal())
                .contents(this.getContents())
                .rating(this.getRating())
                .build();
    }
}
