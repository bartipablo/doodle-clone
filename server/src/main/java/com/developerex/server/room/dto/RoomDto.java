package com.developerex.server.room.dto;

import com.developerex.server.attendee.dto.AttendeeDto;
import com.developerex.server.term.dto.TermDto;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record RoomDto (
    String title,
    String description,
    LocalDateTime deadline,
    AttendeeDto owner,
    List<TermDto> terms,
    List<AttendeeDto> participants){
}