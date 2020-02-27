package com.goal.restservice.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor(
    access =
        AccessLevel
            .PROTECTED) // with this, instance will be generated only through builder pattern.
@AllArgsConstructor
@Getter
@Setter
@Builder // TODO : 모든 필드 x
public class User extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Email private String email;

  @NotNull private String password;

  @NotNull private String userName;

  @NotNull private String firstName;

  @NotNull private String lastName;

  @Size(max = 256)
  @Column(name = "image_url", length = 256)
  private String imageUrl;

  private String introduction;

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
