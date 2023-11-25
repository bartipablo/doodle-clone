package com.developerex.server.attendee;


import com.developerex.server.room.Room;
import lombok.Builder;

import javax.persistence.OneToMany;
import java.util.List;

@Builder
public record AttendeeDto(
     String username,
     String email,
     String password,
      List<Room> participationRooms,
     List<Room> ownedRooms
)
{
}
