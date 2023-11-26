package com.developerex.server.term;

import com.developerex.server.room.RoomDto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TermDto(
        LocalDateTime startDateTime,
        int duration,
        RoomDto room) {
}
