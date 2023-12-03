package com.developerex.server.room;

import com.developerex.server.attendee.AttendeeDto;
import com.developerex.server.term.Term;
import com.developerex.server.attendee.Attendee;
import com.developerex.server.term.TermDto;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record RoomDto (
    String title,
    String description,
    LocalDateTime deadline,
    AttendeeDto owner,
    List<TermDto> terms){
    }
