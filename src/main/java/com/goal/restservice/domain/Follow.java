package com.goal.restservice.domain;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@NoArgsConstructor(
    access =
        AccessLevel
            .PROTECTED) // with this, instance will be generated only through builder pattern.
@AllArgsConstructor
@Getter
@Setter
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"master_id", "slave_id"})})
public class Follow extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne()
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "master_id")
  private User master;

  @ManyToOne
  @JoinColumn(name = "slave_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private User slave;

  @Builder
  public Follow(User master, User slave) {
    this.master = master;
    this.slave = slave;
  }
}
