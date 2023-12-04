package com.developerex.server.term.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TermDto(
        LocalDateTime startDateTime,
        int duration) {
}
