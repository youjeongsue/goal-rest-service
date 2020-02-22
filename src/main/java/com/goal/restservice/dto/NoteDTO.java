package com.goal.restservice.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {
    private String contents;

    private Integer rating;
}
