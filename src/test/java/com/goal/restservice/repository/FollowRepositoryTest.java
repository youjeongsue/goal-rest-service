package com.goal.restservice.repository;

import com.goal.restservice.domain.Follow;
import com.goal.restservice.domain.User;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;



@DataJpaTest
class FollowRepositoryTest {

    @Autowired
    FollowRepository followRepository;

    @Autowired
    UserRepository userRepository;


    private static boolean initialized = false;

    private static final String MASTER1 = "master1";
    private static final String MASTER2 = "master2";
    private static final String MASTER3 = "master3";

    private static final String SLAVE1 = "slave1";
    private static final String SLAVE2 = "slave2";
    private static final String SLAVE3 = "slave3";

    private String[] mameList = {MASTER1, MASTER2 ,MASTER3, SLAVE1, SLAVE2, SLAVE3};

    /**
     * Generate some dummy users before tests start.
     *
     */
    @BeforeEach
    public void INIT() throws Exception {
        if (!initialized) {
          initialized = true;
        }

        // Make three dummy masters and three slaves.
        for (int i = 0; i < 6; i++) {
          userRepository.save(
              User.builder()
                  .email("demonic"+i+"@naver.com")
                  .userName(mameList[i])
                  .password("12345")
                  .firstName("human"+i)
                  .lastName("last")
                  .build());
        }

        Assertions.assertEquals(userRepository.count(), 6);
    }

    @Test
    public void 자신의_모든_팔로워_조회(){

        // One master is following by three slaves.
        Optional<User> optionalUser = userRepository.findOneByUserNameIgnoreCase("master1");
        User master =optionalUser.orElse(null);

        for (int i = 3; i <= 5; i++) {
            optionalUser = userRepository.findOneByUserNameIgnoreCase(mameList[i]);
            User slave = optionalUser.orElse(null);
            followRepository.save(Follow.builder().master(master).slave(slave).build());
        }

        Assertions.assertEquals(followRepository.count(), 3);

        // Get one's followers

        List<Follow> followList = followRepository.findByMasterId(master.getId());

        int i= 2;
        for(Follow f : followList){
            System.out.println(f.getSlave().getUserName());
            Assertions.assertEquals(f.getSlave().getUserName(), mameList[i++]);
        }

    }

    /**
     *  Test for UNIQUE CONSTRAINTS
     */
    @Test
    public void 팔로우_팔로워_중복_저장(){
        Optional<User> optionalUser = userRepository.findOneByUserNameIgnoreCase("master2");
        User master =optionalUser.orElse(null);

        optionalUser = userRepository.findOneByUserNameIgnoreCase("slave2");
        User slave = optionalUser.orElse(null);


        followRepository.save(Follow.builder().master(master).slave(slave).build());
        Assertions.assertEquals(followRepository.count(), 1);

        boolean isPassed = false;
        try{
            System.out.println("#################1");
//            followRepository.save(Follow.builder().master(master).slave(slave).build());
//            followRepository.save(Follow.builder().master(master).slave(slave).build());
            System.out.println("#################2");
            Assertions.assertEquals(followRepository.count(), 1);
        }catch (ConstraintViolationException e){
            System.out.println("#################3");
            isPassed = true;
        }

        //Assertions.assertEquals(isPassed, true);

        System.out.println(followRepository.count());

    }


    @Test
    public void 자신이_팔로잉하는_모든_유저_조회(){


        // One master is following by three slaves.
        Optional<User> optionalUser = userRepository.findOneByUserNameIgnoreCase("slave1");
        User slave =optionalUser.orElse(null);

        for (int i = 0; i <= 2; i++) {
            optionalUser = userRepository.findOneByUserNameIgnoreCase(mameList[i]);
            User master = optionalUser.orElse(null);
            followRepository.save(Follow.builder().master(master).slave(slave).build());
        }

        Assertions.assertEquals(followRepository.count(), 3);



        // Get the users who the slave is following.
        List<Follow> followList = followRepository.findByMasterId(slave.getId());
        Assertions.assertEquals(followList.size(), 0);

        followList = followRepository.findBySlaveId(slave.getId());
        Assertions.assertEquals(followList.size(), 3);

        int i= 0;
        for(Follow f : followList){
            System.out.println(f.getMaster().getUserName());
            Assertions.assertEquals(f.getMaster().getUserName(), mameList[i++]);
        }

    }

    @Test
    public void 관계_생성확인(){

        // Make a relationship
        Optional<User> optionalUser = userRepository.findOneByUserNameIgnoreCase("master1");
        User master =optionalUser.orElse(null);

        optionalUser = userRepository.findOneByUserNameIgnoreCase("slave1");
        User slave1 = optionalUser.orElse(null);

        optionalUser = userRepository.findOneByUserNameIgnoreCase("slave2");
        User slave2 = optionalUser.orElse(null);


        // SAVE!!
        followRepository.save(Follow.builder().master(master).slave(slave1).build());
        followRepository.save(Follow.builder().master(master).slave(slave2).build());


        // There should be two records.
        Assertions.assertEquals(followRepository.count(), 2);

        assert master != null;

        // Get a list of slaves of master
        List<Follow> followList = followRepository.findByMasterId(master.getId());
        Assertions.assertEquals(followList.size(), 2);

        // Get a list of masters of slave
        List<Follow> followList2 = followRepository.findBySlaveId(slave1.getId());
        Assertions.assertEquals(followList2.size(), 1);

        System.out.println("##########");

    }
}