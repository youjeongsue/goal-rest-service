package com.goal.restservice.service;

import com.goal.restservice.domain.Goal;
import com.goal.restservice.domain.Note;
import com.goal.restservice.dto.NoteDTO;
import com.goal.restservice.repository.NoteRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public NoteDTO noteToNoteDTO(Note note){
        return NoteDTO.builder()
                .id(note.getId())
                .user(note.getUser())
                .goal(note.getGoal())
                .contents(note.getContents())
                .rating(note.getRating())
                .build();
    }

    public Note noteDTOToNote(NoteDTO noteDTO){
        Note note =  Note.builder()
                .user(noteDTO.getUser())
                .goal(noteDTO.getGoal())
                .contents(noteDTO.getContents())
                .rating(noteDTO.getRating())
                .build();
        note.setId(noteDTO.getId());
        return note;
    }

    public List<NoteDTO> noteListToNoteDTOList(List<Note> notes){
        List<NoteDTO> notesDTO = new ArrayList<NoteDTO>();
        for(Note note : notes){
            if(note != null){
                notesDTO.add(noteToNoteDTO(note));
            }
        }
        return notesDTO;
    }
    @Override
    public NoteDTO createNote(NoteDTO noteDTO) {
        Note note = noteRepository.save(noteDTOToNote(noteDTO));

        return noteToNoteDTO(note);
    }

    @Override
    public List<NoteDTO> getNoteByUserId(long id) {
        List<Note> notes = noteRepository.findAllByIdOrderByCreatedDate(id);

        return noteListToNoteDTOList(notes);
    }

    @Override
    public List<NoteDTO> getNoteByUserIdAndGoal(long id, Goal goal){
        List<Note> notes = noteRepository.findAllByIdAndGoalOrderByCreatedDate(id, goal);

        return noteListToNoteDTOList(notes);
    }

    @Override
    public NoteDTO getNoteByNoteId(long id) {
        Optional<Note> optionalNote = noteRepository.findById(id);

        return optionalNote.map(note -> noteToNoteDTO(note)).orElse(null);
    }

    @Override
    public NoteDTO updateNote(long id, NoteDTO noteDTO) {
        Optional<Note> oldNote = noteRepository.findById(id);

        if(oldNote.isPresent()){
            Note note = oldNote.get();
            note.update(noteDTO.getContents(), noteDTO.getRating());
            note = noteRepository.save(note);

            return noteToNoteDTO(note);
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
