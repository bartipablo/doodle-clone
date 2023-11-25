package com.developerex.server.term;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public record TermDto(Long id, LocalDateTime startDateTime, String duration, String room) {
}
