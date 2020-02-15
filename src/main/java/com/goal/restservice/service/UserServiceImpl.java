package com.goal.restservice.service;

import com.goal.restservice.domain.User;
import com.goal.restservice.repository.UserRepository;
import com.goal.restservice.util.PasswordEncoding;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoding passwordEncoding = new PasswordEncoding();


    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User userDTO){
        String encodedPassword = passwordEncoding.encode(userDTO.getPassword());

        User user = User.builder().
                name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(encodedPassword)
                .activated(userDTO.isActivated())
                .build();

        return userRepository.save(user);
    }

    /**
     *
     * @param email
     * @param password
     * @return
     */
    @Override
    public User signIn(String email, String rawPassword)
        throws Exception {

        Optional<User> ou = userRepository.findOneByEmailIgnoreCase(email);

        if(ou.isPresent()){
            if( passwordEncoding.matches(rawPassword, ou.get().getPassword()))
                return ou.get();
            else
                //TODO
                throw new Exception("password is not valid");      // password is not valid

        }else{
            //TODO
            throw new Exception("email is not registered");          // email is not registered
        }
    }


    @Override
    public User getUserByEmail(String email){
        Optional<User> optionalUser = userRepository.findOneByEmailIgnoreCase(email);

        return optionalUser.orElse(null);
    }


    @Override
    public User readUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void updateUser(User user){
        String encodedPassword = passwordEncoding.encode(user.getPassword());
        System.out.println(user.getEmail()+" "+user.getId()+" "+encodedPassword);

        userRepository.setUserById(user.getName(), encodedPassword, user.getId());
    }

    @Override
    public boolean isEmailAlreadyUsed(String email){
        return userRepository.findOneByEmailIgnoreCase(email).isPresent();
    }

}
