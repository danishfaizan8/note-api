package com.notekeeper.noteapi.service;

import com.notekeeper.noteapi.dto.NoteDTO;
import com.notekeeper.noteapi.dto.NoteRequest;
import com.notekeeper.noteapi.entity.Note;
import com.notekeeper.noteapi.entity.User;
import com.notekeeper.noteapi.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteDTO createNote(NoteRequest request, User user) {
        Note note = Note.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .user(user)
                .build();

        Note saved = noteRepository.save(note);
        return mapToDTO(saved);
    }

    public List<NoteDTO> getUserNotes(User user) {
        return noteRepository.findByUser(user)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public NoteDTO updateNote(Long id, NoteRequest request, User user) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (!note.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        note.setTitle(request.getTitle());
        note.setContent(request.getContent());

        return mapToDTO(noteRepository.save(note));
    }

    public void deleteNote(Long id, User user) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (!note.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        noteRepository.delete(note);
    }

    private NoteDTO mapToDTO(Note note) {
        return NoteDTO.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .build();
    }
}
