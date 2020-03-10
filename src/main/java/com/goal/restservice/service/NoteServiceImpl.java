package com.goal.restservice.service;

import com.goal.restservice.common.error.GoalDoesNotExistException;
import com.goal.restservice.common.error.SubgoalDoesNotExistException;
import com.goal.restservice.common.error.UserNotExistException;
import com.goal.restservice.domain.*;
import com.goal.restservice.dto.NoteDto;
import com.goal.restservice.repository.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    private final UserRepository userRepository;
    private final GoalRepository goalRepository;
    private final SubgoalRepository subgoalRepository;
    private final NoteRepository noteRepository;


    public NoteServiceImpl(UserRepository userRepository,GoalRepository goalRepository, SubgoalRepository subgoalRepository, NoteRepository noteRepository) {
        this.userRepository = userRepository;
        this.goalRepository = goalRepository;
        this.subgoalRepository = subgoalRepository;
        this.noteRepository = noteRepository;
    }

    public List<NoteDto> noteListToNoteDtoList(List<Note> notes){
        List<NoteDto> notesDTO = new ArrayList<NoteDto>();
        for(Note note : notes){
            if(note != null){
                notesDTO.add(note.noteToNoteDto());
            }
        }
        return notesDTO;
    }

    @Override
    public NoteDto createNote(NoteDto noteDTO) {

        // note validation test and throw exception for user, goal, subgoal
        User user = userRepository.findById(noteDTO.getUser().getId()).get();
        if(user == null)
            throw new UserNotExistException();

        Goal goal = goalRepository.findById(noteDTO.getGoal().getId()).get();
        if(goal == null)
            throw new GoalDoesNotExistException();

        Subgoal subgoal = subgoalRepository.findById(noteDTO.getSubgoal().getId()).get();
        if(subgoal == null)
            throw new SubgoalDoesNotExistException();

        // create note
        Note note = noteRepository.save(noteDTO.noteDtoToNote());

        // add relationship
        user.addNote(note);
        goal.addNote(note);
        subgoal.addNote(note);

        return note.noteToNoteDto();
    }

    @Override
    public List<NoteDto> getNoteByUser(User user) {
        List<Note> notes = noteRepository.findAllByUserOrderByCreatedDate(user);

        return noteListToNoteDtoList(notes);
    }

    @Override
    public List<NoteDto> getNoteByUserAndGoal(User user, Goal goal){
        List<Note> notes = noteRepository.findAllByUserAndGoalOrderByCreatedDate(user, goal);

        return noteListToNoteDtoList(notes);
    }

    @Override
    public NoteDto getNote(long noteId) {
        Optional<Note> optionalNote = noteRepository.findById(noteId);

        return optionalNote.map(note -> note.noteToNoteDto()).orElse(null);
    }

    @Override
    public NoteDto updateNote(long id, NoteDto noteDTO) {
        Optional<Note> oldNote = noteRepository.findById(id);

        if(oldNote.isPresent()){
            Note note = oldNote.get();
            note.update(noteDTO.getSubgoal(), noteDTO.getContents(), noteDTO.getRating());
            note = noteRepository.save(note);

            return note.noteToNoteDto();
        }
        return null;
    }

    @Override
    public boolean deleteNote(long id) {
        Optional<Note> note = noteRepository.findById(id);

        if(note.isPresent()){
            noteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
