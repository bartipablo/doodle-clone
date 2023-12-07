package com.developerex.server.room.mapper;

import com.developerex.server.attendee.mapper.AttendeeMapper;
import com.developerex.server.room.dto.RoomDto;
import com.developerex.server.room.model.Room;
import com.developerex.server.term.mapper.TermMapper;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RoomMapper {

    public static RoomDto mapToDto(Room room) {
        return RoomDto.builder()
                .id(room.getId())
                .title(room.getTitle())
                .description(room.getDescription())
                .deadline(room.getDeadline())
                .owner(AttendeeMapper.mapToDto(room.getOwner()))
                .terms((room.getTerms() != null)
                        ? room.getTerms()
                        .stream()
                        .map(TermMapper::mapToDto)
                        .collect(Collectors.toList())
                        : null)
                .participants(room.getParticipants() != null
                        ? room.getParticipants()
                        .stream()
                        .map(AttendeeMapper::mapToDto)
                        .collect(Collectors.toList())
                        : null)
                .build();
    }

}