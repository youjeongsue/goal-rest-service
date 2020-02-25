package com.goal.restservice.dto;

import com.goal.restservice.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
  private Long id;

  private String email;

  private String password;

  private String userName;

  private String firstName;

  private String lastName;

  private String imageUrl;

  private String introduction;


  @Builder(builderClassName = "ByFollowerBuilder", builderMethodName = "ByFollowerBuilder")
  public UserDTO(User user){
    this.email = user.getEmail();
    this.userName = user.getUserName();
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.imageUrl = user.getImageUrl();
  }
}
