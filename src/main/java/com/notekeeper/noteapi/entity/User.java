package com.notekeeper.noteapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users") // ✅ avoid reserved keyword conflict
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Note> notes = new ArrayList<>();

    // ✅ Custom constructor for (username, password)
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
