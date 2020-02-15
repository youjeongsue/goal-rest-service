package com.goal.restservice.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) // with this, instance will be generated only through builder pattern.
@AllArgsConstructor
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Email
  private String email;

  @NotNull
  private String password;

  @NotNull
  private String userName;

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @CreatedDate
  private LocalDate createdDate;

  @Size(max = 256)
  @Column(name = "image_url", length = 256)
  private String imageUrl;

  private String introduction;

  @Builder
  public User(String email, String password, String userName, String firstName, String lastName, String imageUrl, String introduction){
      this.email = email;
      this.password = password;
      this.userName = userName;
      this.firstName = firstName;
      this.lastName = lastName;
      this.imageUrl = imageUrl;
      this.introduction = introduction;
  }

}
