package com.goal.restservice.web.rest;

import com.goal.restservice.domain.Goal;
import com.goal.restservice.dto.NoteDTO;
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
    public ResponseEntity<NoteDTO> createNote(@RequestBody NoteDTO noteDTO){
        NoteDTO newNoteDTO = noteServiceImpl.createNote(noteDTO);

        return newNoteDTO == null ? new ResponseEntity<NoteDTO>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<NoteDTO>(newNoteDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<NoteDTO>> getNoteByUserId(@PathVariable long userId){
        List<NoteDTO> noteDTOList = noteServiceImpl.getNoteByUserId(userId);

        return noteDTOList == null ? new ResponseEntity<List<NoteDTO>>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<List<NoteDTO>>(noteDTOList, HttpStatus.OK);
    }

    @GetMapping("/{userId}/{goal}")
    public ResponseEntity<List<NoteDTO>> getNoteByUserId(@PathVariable long userId, @PathVariable Goal goal){
        List<NoteDTO> noteDTOList = noteServiceImpl.getNoteByUserIdAndGoal(userId, goal);

        return noteDTOList == null ? new ResponseEntity<List<NoteDTO>>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<List<NoteDTO>>(noteDTOList, HttpStatus.OK);
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<NoteDTO> getNoteByNoteId(@PathVariable long noteId){
        NoteDTO noteDTO = noteServiceImpl.getNoteByNoteId(noteId);

        return noteDTO == null ? new ResponseEntity<NoteDTO>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<NoteDTO>(noteDTO, HttpStatus.OK);
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<NoteDTO> updateNote(@PathVariable long noteId, @RequestBody NoteDTO noteDTO){
        NoteDTO newNoteDTO = noteServiceImpl.updateNote(noteId, noteDTO);

        return newNoteDTO == null ? new ResponseEntity<NoteDTO>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<NoteDTO>(newNoteDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<Void> deleteGoal(@PathVariable long noteId) {
        return noteServiceImpl.deleteNote(noteId) ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
