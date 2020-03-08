package com.goal.restservice.web.rest;

import com.goal.restservice.domain.Goal;
import com.goal.restservice.dto.NoteDto;
import com.goal.restservice.service.NoteServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteServiceImpl noteServiceImpl;

    public NoteController(NoteServiceImpl noteServiceImpl) {
        this.noteServiceImpl = noteServiceImpl;
    }

    @PostMapping
    public ResponseEntity<NoteDto> createNote(@RequestBody NoteDto noteDTO){
        NoteDto newNoteDto = noteServiceImpl.createNote(noteDTO);

        return newNoteDto == null ? new ResponseEntity<NoteDto>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<NoteDto>(newNoteDto, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<NoteDto>> getNoteByUser(@PathVariable long userId){
        List<NoteDto> noteDtoList = noteServiceImpl.getNoteByUser(userId);

        return noteDtoList == null ? new ResponseEntity<List<NoteDto>>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<List<NoteDto>>(noteDtoList, HttpStatus.OK);
    }

    @GetMapping("/{userId}/{goalId}")
    public ResponseEntity<List<NoteDto>> getNoteByUserAndGoal(@PathVariable long userId, @PathVariable Long goalId){
        List<NoteDto> noteDtoList = noteServiceImpl.getNoteByUserAndGoal(userId, goalId);

        return noteDtoList == null ? new ResponseEntity<List<NoteDto>>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<List<NoteDto>>(noteDtoList, HttpStatus.OK);
    }

    @GetMapping("/{noteId}")
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
