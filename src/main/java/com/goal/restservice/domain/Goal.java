package com.goal.restservice.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import jdk.internal.loader.AbstractClassLoaderValue;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
//@ToString(exclude = {"category"})
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"id", "title"}, callSuper = false)
public class Goal extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  private String title;

  //user
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private User user;

  private String desc;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dueDate;

  //note
  @OneToMany(mappedBy = "goal", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  private List<Note> notes  = new ArrayList<>();

  public void addNote(Note note) {
    note.setGoal(this);
    this.notes.add(note);
  }

  //subgoal
  @OneToMany(mappedBy = "goal")
  private List<Subgoal> subgoals  = new ArrayList<>();

  public void addSubGoal(Subgoal subGoal) {
    subGoal.setGoal(this);
    this.subgoals.add(subGoal);
  }

  @Builder
  public Goal(User user, Category category, String title, String desc, LocalDate dueDate) {
    this.user = user;
    this.category = category;
    this.title = title;
    this.desc = desc;
    this.dueDate = dueDate;
  }

  public void updateGoal(Category category, String title, String desc, LocalDate dueDate) {
    if (category != null) {
      setCategory(category);
    }
    if (title != null) {
      this.title = title;
    }
    if (desc != null) {
      this.desc = desc;
    }
    if (dueDate != null) {
      this.dueDate = dueDate;
    }
  }

  public void setUser(User user){
    if(user != null){
      this.user = user;
    }
  }

  public void setCategory(Category category) {
    if (this.category != null && this.category.getGoals() != null) {
      this.category.getGoals().remove(this);
    }

    this.category = category;

    if (category != null) {
      category.getGoals().add(this);
    }
  }

  public Long getId() {
    return id;
  }
}
