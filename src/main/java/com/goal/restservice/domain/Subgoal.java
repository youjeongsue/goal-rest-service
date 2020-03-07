package com.goal.restservice.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

  @ManyToOne
  @JoinColumn(name = "subgoal_id")
  private Goal goal;

  // TODO: Add notes

  public void setGoal(Goal goal) {
    this.goal = goal;
  }

  @Builder
  public Subgoal(Goal goal, String title, String desc, LocalDate dueDate) {
    this.title = title;
    this.goal = goal;
    this.desc = desc;
    this.dueDate = dueDate;
  }

}
