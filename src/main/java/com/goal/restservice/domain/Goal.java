package com.goal.restservice.domain;

import java.time.LocalDate;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@ToString(exclude = {"category"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"id", "title"}, callSuper = false)
public class Goal extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn
  private Category category;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private User user;

  private String title;
  private String desc;
  private LocalDate dueDate;

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
      this.category = category;
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
}
