package com.goal.restservice.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "title"}, callSuper = false)
public class Subgoal {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long goalId;

  @Column(nullable = false, length = 100)
  private String title;
  private String desc;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dueDate;

  @JsonBackReference(value = "subgoal_goal")
  @ManyToOne
  @JoinColumn(name = "temp_goal_id")
  private Goal goal;

  @JsonManagedReference(value = "note_subgoal")
  @OneToMany(mappedBy = "subgoal", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  private List<Note> notes = new ArrayList<Note>();

  public void addNote(Note note){
    note.setSubgoal(this);
    this.notes.add(note);
  }

  @Builder
  public Subgoal(Goal goal, String title, String desc, LocalDate dueDate) {
    this.title = title;
    this.goal = goal;
    this.desc = desc;
    this.dueDate = dueDate;
  }

  public void setGoal(Goal goal) {
    this.goal = goal;
  }
}
