package com.developerex.server.room;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RoomDtoMapper implements Function<Room, RoomDto> {
    @Override
    public RoomDto apply(Room room) {
        return new RoomDto(
                room.getTitle(),
                room.getDescription(),
                room.getDeadline(),
                room.getOwner(),
                room.getTerms(),
                room.getParticipants());
    }
}
