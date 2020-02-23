package com.goal.restservice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "bookList")
    private List<Library> libraryList = new ArrayList<>();

    public void addLibrary(Library library){
        this.libraryList.add(library);
    }

}
