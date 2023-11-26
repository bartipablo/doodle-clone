package com.developerex.server.attendee;


import com.developerex.server.room.RoomMapper;
import java.util.stream.Collectors;


public class AttendeeMapper  {
    public static AttendeeDto mapToDto(Attendee attendee) {
        return AttendeeDto.builder()
                .email(attendee.getEmail())
                .username(attendee.getUsername())
                .password(attendee.getPassword())
                .ownedRooms(attendee.getOwnedRooms()
                        .stream()
                        .map(RoomMapper::mapToDto)
                        .collect(Collectors.toList()))
                .participationRooms(attendee.getParticipationRooms()
                        .stream()
                        .map(RoomMapper::mapToDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public static Attendee mapToEntity(AttendeeDto attendeeDto) {
        return Attendee.builder()
                .email(attendeeDto.email())
                .username(attendeeDto.username())
                .password(attendeeDto.password())
                .ownedRooms(attendeeDto.ownedRooms()
                        .stream()
                        .map(RoomMapper::mapToEntity)
                        .collect(Collectors.toList()))
                .participationRooms(attendeeDto.participationRooms()
                        .stream()
                        .map(RoomMapper::mapToEntity)
                        .collect(Collectors.toSet()))
                .build();
    }
}
