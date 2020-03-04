package com.goal.restservice.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import lombok.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Goal extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne @JoinColumn private Category category;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private User user;

  private String title;
  private String desc;
  private LocalDate dueDate;

  //note와 관계 설정
  @OneToMany(mappedBy = "goal", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  private List<Note> notes = new ArrayList<>();

  public void addNote(Note note){
    note.setGoal(this);
    this.notes.add(note);
  }
}
