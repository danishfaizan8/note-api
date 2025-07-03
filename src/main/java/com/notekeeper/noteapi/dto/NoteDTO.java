package com.notekeeper.noteapi.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteDTO {
    private Long id;
    private String title;
    private String content;
}
