package com.goal.restservice.service;

import com.goal.restservice.common.error.EmailNotMatchedException;
import com.goal.restservice.common.error.PasswordNotMatchedException;
import com.goal.restservice.domain.User;
import com.goal.restservice.dto.UserDTO;
import com.goal.restservice.repository.UserRepository;
import com.goal.restservice.util.PasswordEncoding;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoding passwordEncoding = new PasswordEncoding();

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDTO createUser(UserDTO userDTO) {
    String encodedPassword = passwordEncoding.encode(userDTO.getPassword());

    User user =
        userRepository.save(
            User.builder()
                .email(userDTO.getEmail())
                .password(encodedPassword)
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .username(userDTO.getUsername())
                .imageUrl(userDTO.getImageUrl())
                .introduction(userDTO.getIntroduction())
                .build());

    return UserDTO.builder()
        .email(user.getEmail())
        .username(user.getUsername())
        .firstName(user.getFirstName())
        .lastName((user.getLastName()))
        .introduction(user.getIntroduction())
        .imageUrl(user.getImageUrl())
        .build();
  }

  /**
   *
   */
  @Override
  public User signIn(String email, String rawPassword)
      throws EmailNotMatchedException, PasswordNotMatchedException {

    Optional<User> ou = userRepository.findOneByEmailIgnoreCase(email);
    if (ou.isPresent()) {
      if (passwordEncoding.matches(rawPassword, ou.get().getPassword())) {
        return ou.get();
      } else {
        throw new PasswordNotMatchedException();
      }
    } else {
      throw new EmailNotMatchedException();
    }
  }

  @Override
  public UserDTO getUserByUsername(String username) {
    Optional<User> optionalUser = userRepository.findOneByUsernameIgnoreCase(username);

    return optionalUser
        .map(
            user ->
                UserDTO.builder()
                    .id(null)
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .imageUrl(user.getImageUrl())
                    .introduction(user.getIntroduction())
                    .build())
        .orElse(null);
  }

  @Override
  public UserDTO getUserById(Long id) {
    Optional<User> optionalUser = userRepository.findById(id);

    return optionalUser
        .map(
            user ->
                UserDTO.builder()
                    .id(null)
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .imageUrl(user.getImageUrl())
                    .introduction(user.getIntroduction())
                    .build())
        .orElse(null);
  }

  @Override
  public void updateUser(User user) {
    String encodedPassword = passwordEncoding.encode(user.getPassword());
    System.out.println(user.getEmail() + " " + user.getId() + " " + encodedPassword);

    // userRepository.setUserById(user.getName(), encodedPassword, user.getId());
  }

  @Override
  public boolean isEmailAlreadyUsed(String email) {
    return userRepository.findOneByEmailIgnoreCase(email).isPresent();
  }

  @Override
  public boolean isUsernameAlreadyUsed(String username) {
    return userRepository.findOneByUsernameIgnoreCase(username).isPresent();
  }
}
