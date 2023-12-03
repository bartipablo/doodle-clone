package com.developerex.server.attendee;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;
    public List<AttendeeDto> getAllAttendees() {
        return attendeeRepository
                .findAll()
                .stream()
                .map(AttendeeMapper::mapToDto)
                .collect(Collectors.toList());
    }



    public boolean addAttendee(AttendeeDto attendeeDto) {
        Attendee attendee = AttendeeMapper.mapToEntity(attendeeDto);
        attendeeRepository.save(attendee);
        return true;
    }


}
