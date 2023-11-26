package com.developerex.server.attendee;

import com.developerex.server.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
}