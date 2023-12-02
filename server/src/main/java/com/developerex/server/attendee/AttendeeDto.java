package com.developerex.server.attendee;


import com.developerex.server.room.Room;
import com.developerex.server.room.RoomDto;
import lombok.Builder;

import java.util.List;

@Builder
public record AttendeeDto(
     String username,
     String email,
     String password,
     List<RoomDto> participationRooms,
     List<RoomDto> ownedRooms
)
{
}
