package com.developerex.server.room;

import com.developerex.server.term.Term;
import com.developerex.server.user.Attendee;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
