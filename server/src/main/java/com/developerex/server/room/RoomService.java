package com.developerex.server.room;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

}
