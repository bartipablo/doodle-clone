package com.developerex.server.room;

import com.developerex.server.term.TermMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RoomMapper  {

    public static RoomDto mapToDto(Room room) {
        return RoomDto.builder()
                .title(room.getTitle())
                .description(room.getDescription())
                .deadline(room.getDeadline())
                .owner(room.getOwner())
                .terms(room.getTerms()
                        .stream()
                        .map(TermMapper::mapToDto)
                        .collect(Collectors.toList()))
                .build();

    }

    public static Room mapToEntity(RoomDto roomDto) {
        return Room.builder()
                .title(roomDto.title())
                .description(roomDto.description())
                .deadline(roomDto.deadline())
                .owner(roomDto.owner())
                .terms(roomDto.terms()
                        .stream()
                        .map(TermMapper::mapToTerm)
                        .collect(Collectors.toList()))
                .build();
    }
}