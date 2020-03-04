package com.goal.restservice.repository;

import com.goal.restservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findOneByEmailIgnoreCase(String email);

  Optional<User> findOneByUsernameIgnoreCase(String username);

  //    @Modifying
  //    @Query("update User u set u.name = ?1, u.password = ?2 where u.id = ?3")
  //    void setUserById(String name, String password, Long id);

}
