package com.goal.restservice.service;

import com.goal.restservice.domain.Goal;
import com.goal.restservice.dto.NoteDto;

import java.util.List;

public interface NoteService {
    NoteDto createNote(NoteDto noteDTO);

    List<NoteDto> getNoteByUser(long id);

    List<NoteDto> getNoteByUserAndGoal(long userId, long goalId);

    NoteDto getNote(long noteId);

    NoteDto updateNote(long id, NoteDto noteDTO);

    boolean deleteNote(long id);
}
