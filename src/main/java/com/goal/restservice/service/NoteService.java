package com.goal.restservice.service;

import com.goal.restservice.domain.Goal;
import com.goal.restservice.dto.NoteDTO;

import java.util.List;

public interface NoteService {
    NoteDTO createNote(NoteDTO noteDTO);

    List<NoteDTO> getNoteByUserId(long id);

    List<NoteDTO> getNoteByUserIdAndGoal(long id, Goal goal);

    NoteDTO getNoteByNoteId(long id);

    NoteDTO updateNote(long id, NoteDTO noteDTO);

    boolean deleteNote(long id);
}
