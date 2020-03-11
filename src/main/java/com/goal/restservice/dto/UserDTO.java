package com.goal.restservice.dto;

import com.goal.restservice.domain.User;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
  private Long id;

  private String email;

  private String password;

  private String username;

  private String firstName;

  private String lastName;

  private String imageUrl;

  private String introduction;

  @Builder(builderClassName = "ByFollowerBuilder", builderMethodName = "ByFollowerBuilder")
  public UserDTO(User user) {
    this.email = user.getEmail();
    //TODO
    this.password = user.getPassword();
    this.username = user.getUsername();
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.imageUrl = user.getImageUrl();
  }
}
