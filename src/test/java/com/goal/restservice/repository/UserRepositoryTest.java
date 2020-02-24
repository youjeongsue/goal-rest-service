package com.goal.restservice.repository;


import com.goal.restservice.domain.User;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;


    @Test
    public void 유저_생성하기() {
        //given
        userRepository.save(User.builder()
                .email("demoni@naver.com")
                .userName("hello")
                .password("12345")
                .firstName("first")
                .lastName("last")
//                .activated(false)
                .build());

        userRepository.save(User.builder()
                .email("demoni@naver.com")
                .userName("hello")
                .password("12345")
                .firstName("first")
                .lastName("last")
//                .activated(false)
                .build());

        System.out.println("#################################");


        //when
        List<User> userList = userRepository.findAll();


        //then
        User users = userList.get(0);
        System.out.println(users.getEmail());
        System.out.println((users.getId()));

        Optional<User> user = userRepository.findById(users.getId());

        System.out.println(user.get().getId());
    }

}