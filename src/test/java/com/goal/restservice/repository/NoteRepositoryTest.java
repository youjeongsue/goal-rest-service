package com.goal.restservice.repository;

import com.goal.restservice.domain.Category;
import com.goal.restservice.domain.Goal;
import com.goal.restservice.domain.Note;
import com.goal.restservice.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class NoteRepositoryTest {
    @Autowired
    NoteRepository noteRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GoalRepository goalRepository;

    @Test
    public void create_note(){
        User user = User.builder()
                .email("demoni@naver.com")
                .userName("hello")
                .password("12345")
                .firstName("first")
                .lastName("last")
                .build();
        Category category = Category.builder().name("category1").build();

        userRepository.save(user);
        categoryRepository.save(category);
        Goal goal = Goal.builder().user(user).category(category).title("title").desc("desc").build();

        goalRepository.save(goal);
        noteRepository.save(Note.builder()
                .user(user)
                .goal(goal)
                .contents("아주 보람찬 하루였다")
                .rating(5)
                .build());

        System.out.println("---------------------TEST---------------------");

        List<Note> noteList = noteRepository.findAll();

        Note test_note_1 = noteList.get(0);
        System.out.println(test_note_1.getContents());
        System.out.println(test_note_1.getRating());
        System.out.println(test_note_1.getId());

        Optional<Note> test_note_2 = noteRepository.findById(test_note_1.getId());

        System.out.println(test_note_2.get().getId());
    }
}
