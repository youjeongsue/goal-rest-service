package com.goal.restservice.service;

import com.goal.restservice.dto.NoteDTO;

import java.util.List;

public interface NoteService {
    NoteDTO createNote(NoteDTO noteDTO);

    List<NoteDTO> getNoteByUserId(long id);

    NoteDTO getNoteById(long id);

    NoteDTO updateNote(long id, NoteDTO noteDTO);

    boolean deleteNote(long id);
}
