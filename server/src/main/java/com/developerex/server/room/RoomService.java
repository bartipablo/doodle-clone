package com.developerex.server.room;

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
}
