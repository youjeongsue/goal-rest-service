package com.goal.restservice.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
//@ToString(exclude = "goals")
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "name"}, callSuper = false)
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String name;

  @OneToMany(mappedBy = "category")
  private List<Goal> goals = new ArrayList<Goal>();

  public void addGoal(Goal goal) {
    goal.setCategory(this);
    this.goals.add(goal);
  }

  @Builder
  public Category(String name) {
    this.name = name;
  }

}
