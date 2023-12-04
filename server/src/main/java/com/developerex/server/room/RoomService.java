package com.developerex.server.room;

import com.developerex.server.attendee.dto.AttendeeDto;
import com.developerex.server.attendee.mapper.AttendeeMapper;
import com.developerex.server.room.dto.RoomDto;
import com.developerex.server.room.mapper.RoomMapper;
import com.developerex.server.room.model.Room;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public List<RoomDto> getAllRooms() {
        return roomRepository.findAll()
                .stream()
                .map(RoomMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public boolean addRoom(RoomDto roomDto) {
        Room room = RoomMapper.mapToEntity(roomDto);
        roomRepository.save(room);
        return true;
    }

    public List<RoomDto> getAllRoomsOwnedByUserId(Long userId) {
        return roomRepository.findAllByOwnerId(userId).orElseThrow(() -> new EntityNotFoundException("No rooms found for user with id: " + userId))
                .stream()
                .map(RoomMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<RoomDto> getAllRoomsParticipatedByUserId(Long userId) {
        return roomRepository.findAllByParticipantsId(userId).orElseThrow(() -> new EntityNotFoundException("No rooms found for user with id: " + userId))
                .stream()
                .map(RoomMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public RoomDto getRoomById(Long roomId) {
        return RoomMapper.mapToDto(roomRepository.findById(roomId).orElseThrow(() -> new EntityNotFoundException("No room found with id: " + roomId)));
    }

    public List<AttendeeDto> getRoomAttendees(Long roomId) {
        return roomRepository.findById(roomId).orElseThrow(() -> new EntityNotFoundException("No room found with id: " + roomId))
                .getParticipants()
                .stream()
                .map(AttendeeMapper::mapToDto)
                .collect(Collectors.toList());
    }

//  TODO: Implement this method
//    public List<AttendeeDto> getRoomInfo(Long roomId) {
//        return roomRepository.findById(roomId).orElseThrow(() -> new EntityNotFoundException("No room found with id: " + roomId))
//                .getParticipants()
//                .stream()
//                .map(AttendeeMapper::mapToDto)
//                .collect(Collectors.toList());
//    }
}
