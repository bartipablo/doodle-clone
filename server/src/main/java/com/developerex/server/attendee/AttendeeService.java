package com.developerex.server.attendee;

import com.developerex.server.term.Term;
import com.developerex.server.term.TermDto;
import com.developerex.server.term.TermMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
        Attendee attendee = AttendeeMapper.mapToAttendee(attendeeDto);
        attendeeRepository.save(attendee);
        return true;
    }

}
