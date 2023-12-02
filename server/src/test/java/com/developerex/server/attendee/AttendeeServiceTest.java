package com.developerex.server.attendee;

import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Arrays;
import java.util.List;

import static com.developerex.server.attendee.AttendeeMapper.mapToDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AttendeeServiceTest {


    @Mock
    private AttendeeRepository attendeeRepository;

    @InjectMocks
    private AttendeeService attendeeService;

    @Test
    public void testGetAllAttendees() {
        //GIVEN
        Attendee attendee1 = new Attendee(1L, "Jan", "Kowalski", "jan@example.com", null, null);
        Attendee attendee2 = new Attendee(2L, "Anna", "Nowak", "anna@example.com", null, null);


        //WHEN
        Mockito.when(attendeeRepository.findAll()).thenReturn(Arrays.asList(attendee1, attendee2));

        List<AttendeeDto> attendeeDtos = attendeeService.getAllAttendees();

        //THEN
        assertAll("Attendee List",
                () -> assertFalse(attendeeDtos.isEmpty(), "Attendee list should not be empty"),
                () -> assertEquals(2, attendeeDtos.size(), "Attendee list should contain 2 elements"),
                () -> assertTrue(attendeeDtos.contains(mapToDto(attendee1)), "Attendee list should contain attendee1"),
                () -> assertTrue(attendeeDtos.contains(mapToDto(attendee2)), "Attendee list should contain attendee2")
        );
    }

    @Test
    void testAddAttendee() {
        //GIVEN
        AttendeeDto attendeeDto = mapToDto(new Attendee(1L, "Jan", "Kowalski", "jan@example.com", null, null));

        //WHEN
        boolean result = attendeeService.addAttendee(attendeeDto);
        ArgumentCaptor<Attendee> attendeeCaptor = ArgumentCaptor.forClass(Attendee.class);

        //THEN
        verify(attendeeRepository, times(1)).save(attendeeCaptor.capture());
        Attendee capturedAttendee = attendeeCaptor.getValue();
        assertTrue(result);

        assertEquals(attendeeDto, mapToDto(capturedAttendee));
    }

}