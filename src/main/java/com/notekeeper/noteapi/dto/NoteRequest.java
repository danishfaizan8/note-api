package com.notekeeper.noteapi.dto;

import lombok.Data;

@Data
public class NoteRequest {
    private String title;
    private String content;
}
