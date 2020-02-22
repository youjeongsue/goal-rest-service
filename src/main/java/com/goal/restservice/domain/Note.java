package com.goal.restservice.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Note extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Goal goal;

    @ManyToOne
    private User user;

    private LocalDate creationDate;

    private String contents;
    private Integer rating;

    @Builder
    public Note(String contents, Integer rating){
        this.contents = contents;
        this.rating = rating;
    }
}
