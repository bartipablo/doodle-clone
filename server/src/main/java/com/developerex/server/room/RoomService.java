package com.developerex.server.room;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    private final RoomDtoMapper roomDtoMapper;

    public List<RoomDto> getAllRooms() {
        return roomRepository.findAll()
                .stream()
                .map(roomDtoMapper)
                .collect(Collectors.toList());
    }

}
