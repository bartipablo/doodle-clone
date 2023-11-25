package com.developerex.server.room;

import com.developerex.server.term.Term;
import com.developerex.server.attendee.Attendee;
import java.time.LocalDateTime;
import java.util.List;


public record RoomDto (
    String title,
    String description,
    LocalDateTime deadline,
    Attendee owner,
    List<Term> terms,
    List<Attendee> participants
        ){
    }
