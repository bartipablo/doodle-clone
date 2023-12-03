package com.developerex.server.attendee;


import com.developerex.server.room.RoomMapper;
import org.springframework.web.bind.annotation.Mapping;

import java.util.Collections;
import java.util.stream.Collectors;

public class AttendeeMapper  {

    public static AttendeeDto mapToDto(Attendee attendee) {
        return AttendeeDto.builder()
                .email(attendee.getEmail())
                .username(attendee.getUsername())
                .password(attendee.getPassword())
                .ownedRooms((attendee.getOwnedRooms() != null)
                        ? attendee.getOwnedRooms()
                        .stream()
                        .map(RoomMapper::mapToDto)
                        .collect(Collectors.toList())
                        : Collections.emptyList())
                .participationRooms((attendee.getParticipationRooms() != null)
                        ? attendee.getParticipationRooms()
                        .stream()
                        .map(RoomMapper::mapToDto)
                        .collect(Collectors.toList())
                        : Collections.emptyList())
                .build();
    }

    public static Attendee mapToEntity(AttendeeDto attendeeDto) {
        return Attendee.builder()
                .email(attendeeDto.email())
                .username(attendeeDto.username())
                .password(attendeeDto.password())
                .ownedRooms((attendeeDto.ownedRooms() != null)
                        ? attendeeDto.ownedRooms()
                        .stream()
                        .map(RoomMapper::mapToEntity)
                        .collect(Collectors.toList())
                        : Collections.emptyList())
                .participationRooms((attendeeDto.participationRooms() != null)
                        ? attendeeDto.participationRooms()
                        .stream()
                        .map(RoomMapper::mapToEntity)
                        .collect(Collectors.toSet())
                        : Collections.emptySet())
                .build();
    }
}
