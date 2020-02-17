package com.goal.restservice.dto;

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


}
