package com.goal.restservice.service;

import com.goal.restservice.domain.Goal;
import com.goal.restservice.dto.NoteDto;

import java.util.List;

public interface NoteService {
    NoteDto createNote(NoteDto noteDTO);

    List<NoteDto> getNoteByUserId(long id);

    List<NoteDto> getNoteByUserIdAndGoal(long id, Goal goal);

    NoteDto getNoteByNoteId(long id);

    NoteDto updateNote(long id, NoteDto noteDTO);

    boolean deleteNote(long id);
}
