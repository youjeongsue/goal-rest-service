package com.goal.restservice.service;

import com.goal.restservice.domain.User;
import com.goal.restservice.dto.UserDTO;
import com.goal.restservice.repository.FollowRepository;
import com.goal.restservice.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/** NOTE) For now, each test should be executed one by one. */
@SpringBootTest
class FollowServiceImplTest {
  private static final String DUMMY = "dummy";
  private static boolean initialized = false;
  @Autowired FollowServiceImpl followServiceImpl;

  @Autowired UserRepository userRepository;

  @Autowired FollowRepository followRepository;

  @BeforeEach
  public void INIT() throws Exception {
    if (!initialized) initialized = true;

    // Make three dummy masters and three slaves.
    for (int i = 0; i < 6; i++) {
      userRepository.save(
          User.builder()
              .email("demonic" + i + "@naver.com")
              .userName(DUMMY + i)
              .password("12345")
              .firstName("human" + i)
              .lastName("last")
              .build());
    }

    Assertions.assertEquals(6, userRepository.count());
  }

  @Test
  public void 나의_팔로워_조회() {

    // given
    User star = userRepository.findOneByUserNameIgnoreCase(DUMMY + 0).orElse(null);
    Assertions.assertNotNull(star);

    for (int i = 1; i <= 3; i++) {
      User fan = userRepository.findOneByUserNameIgnoreCase(DUMMY + i).orElse(null);
      Assertions.assertNotNull(fan);

      followServiceImpl.userIdFollowUserDTO(
          fan.getId(), UserDTO.builder().userName(star.getUserName()).build());
    }
    Assertions.assertEquals(followRepository.count(), 3);

    // when
    List<UserDTO> followerList = followServiceImpl.getAllFollower(star.getId());

    // then
    Assertions.assertEquals(3, followerList.size());

    for (int i = 1; i <= 3; i++) {
      Assertions.assertEquals(followerList.get(i - 1).getUserName(), DUMMY + i);
      Assertions.assertEquals(followerList.get(i - 1).getFirstName(), "human" + i);
    }
  }

  @Test
  public void 내가_팔로워_중인_유저_조회() {
    // given
    User fan = userRepository.findOneByUserNameIgnoreCase(DUMMY + 0).orElse(null);
    Assertions.assertNotNull(fan);

    for (int i = 1; i <= 3; i++) {
      User star = userRepository.findOneByUserNameIgnoreCase(DUMMY + i).orElse(null);
      Assertions.assertNotNull(star);

      followServiceImpl.userIdFollowUserDTO(
          fan.getId(), UserDTO.builder().userName(star.getUserName()).build());
    }
    Assertions.assertEquals(followRepository.count(), 3);

    // when
    List<UserDTO> userDTOList = followServiceImpl.getAllFollowed(fan.getId());

    // then
    Assertions.assertEquals(3, userDTOList.size());
  }

  @Test
  public void 언팔로우() {
    // given
    User fan = userRepository.findOneByUserNameIgnoreCase(DUMMY + 0).orElse(null);
    Assertions.assertNotNull(fan);

    for (int i = 1; i <= 3; i++) {
      User star = userRepository.findOneByUserNameIgnoreCase(DUMMY + i).orElse(null);
      Assertions.assertNotNull(star);

      followServiceImpl.userIdFollowUserDTO(
          star.getId(), UserDTO.builder().userName(fan.getUserName()).build());
    }
    Assertions.assertEquals(followRepository.count(), 3);

    // when
    User notInterestingAnyMore = userRepository.findOneByUserNameIgnoreCase(DUMMY + 1).orElse(null);

    List<UserDTO> followerList = followServiceImpl.getAllFollower(notInterestingAnyMore.getId());
    Assertions.assertEquals(1, followerList.size());

    followServiceImpl.unfollow(
        fan.getId(), UserDTO.builder().userName(notInterestingAnyMore.getUserName()).build());

    // then
    List<UserDTO> emptyFollowerList =
        followServiceImpl.getAllFollower(notInterestingAnyMore.getId());
    Assertions.assertEquals(0, emptyFollowerList.size());

    List<UserDTO> follwedList = followServiceImpl.getAllFollowed(fan.getId());
    Assertions.assertEquals(2, follwedList.size());
  }

  @Test
  public void 팔루워_추가() {
    // given
    User star = userRepository.findOneByUserNameIgnoreCase(DUMMY + 0).orElse(null);

    // when
    for (int i = 1; i <= 3; i++) {
      User fan = userRepository.findOneByUserNameIgnoreCase(DUMMY + i).orElse(null);
      assert fan != null;
      followServiceImpl.userIdFollowUserDTO(
          fan.getId(), UserDTO.builder().userName(fan.getUserName()).build());
    }

    // then
    Assertions.assertEquals(followRepository.count(), 3);
  }
}
