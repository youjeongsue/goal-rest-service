package com.goal.restservice.domain;

import java.util.List;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// with this, instance will be generated only through builder pattern.
@ToString
@Getter
@Setter
public class User extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Email
  private String email;

  @NotNull
  private String password;

  @NotNull

  private String username;

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @Size(max = 256)
  @Column(name = "image_url", length = 256)
  private String imageUrl;

  private String introduction;

  @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  private List<Note> notes = new ArrayList<>();

  public void addNote(Note note) {
    note.setUser(this);
    this.notes.add(note);
  }

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  private List<Goal> goals;

  @Builder
  public User(String email, String password, String username, String firstName, String lastName,
      String imageUrl,
      String introduction) {
    this.email = email;
    this.password = password;
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.introduction = introduction;
    this.imageUrl = imageUrl;
  }

  //  @OneToMany(mappedBy = "master", cascade = CascadeType.REMOVE)     // 실제 디비 스키마에는 영향이 없다. -
  // 무시된다.
  //  private List<Follow> masters = new ArrayList<>();
  //
  //  @OneToMany(mappedBy = "slave")
  //  private List<Follow> slaves = new ArrayList<>();

  //
  //  public void addMaster(Follow follow){
  //    masters.add(follow);
  //  }
  //
  //  public List<Follow> getMasters(){
  //    return masters;
  //  }
  //
  //  public List<Follow> getSlaves(){
  //    return slaves;
  //  }
}
