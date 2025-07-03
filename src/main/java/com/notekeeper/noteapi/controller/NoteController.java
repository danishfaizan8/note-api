package com.notekeeper.noteapi.controller;

import com.notekeeper.noteapi.dto.NoteDTO;
import com.notekeeper.noteapi.dto.NoteRequest;
import com.notekeeper.noteapi.entity.User;
import com.notekeeper.noteapi.service.NoteService;
import com.notekeeper.noteapi.util.AuthUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;
    private final AuthUtil authUtil;

    @PostMapping
    public ResponseEntity<NoteDTO> create(@RequestBody @Valid NoteRequest request) {
        User currentUser = authUtil.getCurrentUser();
        return ResponseEntity.status(201).body(noteService.createNote(request, currentUser));
    }

    @GetMapping
    public ResponseEntity<List<NoteDTO>> getAll() {
        User currentUser = authUtil.getCurrentUser();
        return ResponseEntity.ok(noteService.getUserNotes(currentUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteDTO> update(@PathVariable Long id, @RequestBody @Valid NoteRequest request) {
        User currentUser = authUtil.getCurrentUser();
        return ResponseEntity.ok(noteService.updateNote(id, request, currentUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        User currentUser = authUtil.getCurrentUser();
        noteService.deleteNote(id, currentUser);
        return ResponseEntity.noContent().build();
    }
}
