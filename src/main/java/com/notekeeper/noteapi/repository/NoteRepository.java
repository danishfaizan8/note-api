package com.notekeeper.noteapi.repository;

import com.notekeeper.noteapi.entity.Note;
import com.notekeeper.noteapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUser(User user);
}
