package com.goal.restservice.domain;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
public class Note extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "goal_id")
    private Goal goal;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "subgoal_id")
    private Subgoal subgoal;

    @CreatedDate
    private LocalDateTime createdDate;

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

    public void setId(Long id) { this.id = id; }

    public void setUser(User user){ this.user = user; }

    public void setGoal(Goal goal){ this.goal = goal; }

    public void setSubgoal(Subgoal subgoal){ this.subgoal = subgoal; }
}
