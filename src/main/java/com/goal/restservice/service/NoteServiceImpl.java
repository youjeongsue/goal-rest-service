package com.goal.restservice.service;

import com.goal.restservice.domain.Goal;
import com.goal.restservice.domain.Note;
import com.goal.restservice.dto.NoteDto;
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

    public NoteDto noteToNoteDTO(Note note){
        return NoteDto.builder()
                .id(note.getId())
                .user(note.getUser())
                .goal(note.getGoal())
                .contents(note.getContents())
                .rating(note.getRating())
                .build();
    }

    public Note noteDTOToNote(NoteDto noteDTO){
        Note note =  Note.builder()
                .user(noteDTO.getUser())
                .goal(noteDTO.getGoal())
                .contents(noteDTO.getContents())
                .rating(noteDTO.getRating())
                .build();
        note.setId(noteDTO.getId());
        return note;
    }

    public List<NoteDto> noteListToNoteDTOList(List<Note> notes){
        List<NoteDto> notesDTO = new ArrayList<NoteDto>();
        for(Note note : notes){
            if(note != null){
                notesDTO.add(noteToNoteDTO(note));
            }
        }
        return notesDTO;
    }
    @Override
    public NoteDto createNote(NoteDto noteDTO) {
        Note note = noteRepository.save(noteDTOToNote(noteDTO));

        return noteToNoteDTO(note);
    }

    @Override
    public List<NoteDto> getNoteByUserId(long id) {
        List<Note> notes = noteRepository.findAllByIdOrderByCreatedDate(id);

        return noteListToNoteDTOList(notes);
    }

    @Override
    public List<NoteDto> getNoteByUserIdAndGoal(long id, Goal goal){
        List<Note> notes = noteRepository.findAllByIdAndGoalOrderByCreatedDate(id, goal);

        return noteListToNoteDTOList(notes);
    }

    @Override
    public NoteDto getNoteByNoteId(long id) {
        Optional<Note> optionalNote = noteRepository.findById(id);

        return optionalNote.map(note -> noteToNoteDTO(note)).orElse(null);
    }

    @Override
    public NoteDto updateNote(long id, NoteDto noteDTO) {
        Optional<Note> oldNote = noteRepository.findById(id);

        if(oldNote.isPresent()){
            Note note = oldNote.get();
            note.update(noteDTO.getSubgoal(), noteDTO.getContents(), noteDTO.getRating());
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
