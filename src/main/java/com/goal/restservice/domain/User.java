package com.goal.restservice.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Entity
//@NoArgsConstructor(access = AccessLevel.PROTECTED) // with this, instance will be generated only through builder pattern.
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Email
  private String email;

  @NotNull
  private String password;

&   private String name;

  @NotNull                    //  db에 쿼리 날리기 전 app level에서 error 발생
  // @Column(nullable = false) :  이렇게 하면 db에 query를 날린 후에 error 발생
  private boolean activated;

  @CreatedDate
  private LocalDate createdDate;

  @Builder
  public User(String email, String password, String name, boolean activated){
      this.email = email;
      this.password = password;
      this.name = name;
      this.activated = activated;
  }

}
