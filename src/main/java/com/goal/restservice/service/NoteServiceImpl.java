package com.goal.restservice.service;

import com.goal.restservice.domain.Note;
import com.goal.restservice.dto.GoalDto;
import com.goal.restservice.dto.NoteDTO;
import com.goal.restservice.dto.UserDTO;
import com.goal.restservice.repository.NoteRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    private final GoalServiceImpl goalServiceImpl;
    private final UserServiceImpl userServiceImpl;
    private final NoteRepository noteRepository;

    public NoteServiceImpl(GoalServiceImpl goalServiceImpl, UserServiceImpl userServiceImpl, NoteRepository noteRepository) {
        this.goalServiceImpl = goalServiceImpl;
        this.userServiceImpl = userServiceImpl;
        this.noteRepository = noteRepository;
    }


    @Override
    public NoteDTO createNote(NoteDTO noteDTO) {
        UserDTO user =  userServiceImpl.createUser(
                UserDTO.builder()
                        .userName("test_user").build());

        GoalDto goal =  goalServiceImpl.createGoal(
                GoalDto.builder()
                        .category("English").title("test_goal_title").desc("test~").build());

        Note note = noteRepository.save(
                Note.builder()
                        .contents(noteDTO.getContents())
                        .rating(noteDTO.getRating())
                        .build()
        );

        return null;
    }

    @Override
    public List<NoteDTO> getNoteByUserId(long id) {
        return null;
    }

    @Override
    public NoteDTO getNoteById(long id) {
        return null;
    }

    @Override
    public NoteDTO updateNote(long id, NoteDTO noteDTO) {
        return null;
    }

    @Override
    public boolean deleteNote(long id) {
        return false;
    }
}
