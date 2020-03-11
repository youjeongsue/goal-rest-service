package com.goal.restservice.web.rest;

import com.goal.restservice.common.error.NoteCreateFailException;
import com.goal.restservice.domain.Goal;
import com.goal.restservice.domain.User;
import com.goal.restservice.dto.NoteDto;
import com.goal.restservice.repository.GoalRepository;
import com.goal.restservice.repository.UserRepository;
import com.goal.restservice.service.NoteServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteServiceImpl noteServiceImpl;
    private final UserRepository userRepository;
    private final GoalRepository goalRepository;

    public NoteController(NoteServiceImpl noteServiceImpl, UserRepository userRepository, GoalRepository goalRepository) {
        this.noteServiceImpl = noteServiceImpl;
        this.userRepository = userRepository;
        this.goalRepository = goalRepository;
    }

    @PostMapping
    public ResponseEntity<NoteDto> createNote(@RequestBody NoteDto noteDTO){
        try{
            NoteDto new_note = noteServiceImpl.createNote(noteDTO);

            return new ResponseEntity<NoteDto>(new_note, HttpStatus.CREATED);
        }catch (Exception e){
            throw new NoteCreateFailException();
        }
    }

    @GetMapping("/me/{userId}")
    public ResponseEntity<List<NoteDto>> getNoteByUser(@PathVariable long userId){
        User user = userRepository.findById(userId).get();

        List<NoteDto> noteDtoList = noteServiceImpl.getNoteByUser(user);

        return noteDtoList == null ? new ResponseEntity<List<NoteDto>>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<List<NoteDto>>(noteDtoList, HttpStatus.OK);
    }

    @GetMapping("/{userId}/{goalId}")
    public ResponseEntity<List<NoteDto>> getNoteByUserAndGoal(@PathVariable long userId, @PathVariable long goalId){
        User user = userRepository.findById(userId).get();
        Goal goal = goalRepository.findById(goalId).get();

        List<NoteDto> noteDtoList = noteServiceImpl.getNoteByUserAndGoal(user, goal);

        return noteDtoList == null ? new ResponseEntity<List<NoteDto>>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<List<NoteDto>>(noteDtoList, HttpStatus.OK);
    }

    @GetMapping("/note/{noteId}")
    public ResponseEntity<NoteDto> getNoteByNoteId(@PathVariable long noteId){
        NoteDto noteDTO = noteServiceImpl.getNote(noteId);

        return noteDTO == null ? new ResponseEntity<NoteDto>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<NoteDto>(noteDTO, HttpStatus.OK);
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<NoteDto> updateNote(@PathVariable long noteId, @RequestBody NoteDto noteDTO){
        NoteDto newNoteDto = noteServiceImpl.updateNote(noteId, noteDTO);

        return newNoteDto == null ? new ResponseEntity<NoteDto>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<NoteDto>(newNoteDto, HttpStatus.OK);
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<Void> deleteGoal(@PathVariable long noteId) {
        return noteServiceImpl.deleteNote(noteId) ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
