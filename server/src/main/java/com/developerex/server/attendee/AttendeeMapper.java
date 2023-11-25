package com.developerex.server.attendee;


import com.developerex.server.term.Term;
import com.developerex.server.term.TermDto;
import org.springframework.stereotype.Service;


public class AttendeeMapper  {
    public static AttendeeDto mapToDto(Attendee attendee) {
        return AttendeeDto.builder()
                .email(attendee.getEmail())
                .username(attendee.getUsername())
                .password(attendee.getPassword())
                .ownedRooms(attendee.getOwnedRooms())
                .participationRooms(attendee.getParticipationRooms())
                .build();
    }

    public static Attendee mapToAttendee(AttendeeDto attendeeDto) {
        return Attendee.builder()
                .email(attendeeDto.email())
                .username(attendeeDto.username())
                .password(attendeeDto.password())
                .ownedRooms(attendeeDto.ownedRooms())
                .participationRooms(attendeeDto.participationRooms())
                .build();
    }
}
