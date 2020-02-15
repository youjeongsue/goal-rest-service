package com.goal.restservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class userDTO {
    private Long id;

    private String email;

    private String userName;

    private String firstName;

    private String lastName;

    private String imageUrl;

    private String introduction;

    @Builder
    public userDTO(Long id, String email, String userName, String firstName, String lastName, String imageUrl, String introduction) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageUrl = imageUrl;
        this.introduction = introduction;
    }
}
