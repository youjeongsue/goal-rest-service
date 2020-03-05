package com.goal.restservice.repository;

import com.goal.restservice.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class UserRepositoryTest {

  @Autowired UserRepository userRepository;

  @Test
  public void 유저_생성하기() {
    // given
    userRepository.save(
        User.builder()
            .email("demoni@naver.com")
            .userName("hello")
            .password("12345")
            .firstName("first")
            .lastName("last")
            //                .activated(false)
            .build());

    userRepository.save(
        User.builder()
            .email("demoni@naver.com")
            .userName("hello")
            .password("12345")
            .firstName("first")
            .lastName("last")
            //                .activated(false)
            .build());

    System.out.println("#################################");

    // when
    List<User> userList = userRepository.findAll();

    // then
    User users = userList.get(0);
    System.out.println(users.getEmail());
    System.out.println((users.getId()));

    Optional<User> user = userRepository.findById(users.getId());

    System.out.println(user.get().getId());
  }
}
