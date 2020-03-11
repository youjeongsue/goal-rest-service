package com.goal.restservice.service;

import com.goal.restservice.domain.Goal;
import com.goal.restservice.domain.User;
import com.goal.restservice.dto.NoteDto;

import java.util.List;

public interface NoteService {
    NoteDto createNote(NoteDto noteDTO);

    List<NoteDto> getNoteByUser(User user);

    List<NoteDto> getNoteByUserAndGoal(User user, Goal goal);

    NoteDto getNote(long noteId);

    NoteDto updateNote(long id, NoteDto noteDTO);

    boolean deleteNote(long id);
}
