package com.developerex.server;

import com.developerex.server.attendee.Attendee;
import com.developerex.server.attendee.AttendeeRepository;
import com.developerex.server.room.Room;
import com.developerex.server.room.RoomRepository;
import com.developerex.server.term.Term;
import com.developerex.server.term.TermRepository;
import com.developerex.server.vote.Vote;
import com.developerex.server.vote.VoteRepository;
import com.developerex.server.vote.VoteType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Configuration
public class ExampleConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(RoomRepository roomRepository, AttendeeRepository attendeeRepository,
                                        TermRepository termRepository, VoteRepository voteRepository) {
        return args -> {
            if (roomRepository.count() == 0) {

                var attendee1 = Attendee.builder()
                        .username("Example name 1")
                        .email("example1@example.com")
                        .password("examplepassword1")
                        .participationRooms(new HashSet<>())
                        .ownedRooms(new ArrayList<>())
                        .build();

                var attendee2 = Attendee.builder()
                        .username("Example name 2")
                        .email("example2@example.com")
                        .password("examplepassword2")
                        .participationRooms(new HashSet<>())
                        .ownedRooms(new ArrayList<>())
                        .build();

                var attendee3 = Attendee.builder()
                        .username("Example name 3")
                        .email("example3@example.com")
                        .password("examplepassword3")
                        .participationRooms(new HashSet<>())
                        .ownedRooms(new ArrayList<>())
                        .build();

                attendeeRepository.saveAll(List.of(attendee1, attendee2, attendee3));

                var room = Room.builder()
                        .title("Example title 1")
                        .description("Example description 1")
                        .deadline(LocalDate.now().atStartOfDay())
                        .terms(new ArrayList<>())
                        .participants(new HashSet<>())
                        .owner(attendee2)
                        .build();

                roomRepository.save(room);

                attendee2.addOwnedRoom(room);

                attendeeRepository.save(attendee2);


//                //TO FIX!!!!!
//                attendee1.addParticipationRoom(room);
//                room.addParticipant(attendee1);
//
//                attendeeRepository.save(attendee1);
//                roomRepository.save(room);
//                //TO FIX!!!!!

                var term1 = Term.builder()
                        .startDateTime(LocalDate.now().atStartOfDay())
                        .duration(60)
                        .votes(new ArrayList<>())
                        .room(room)
                        .build();

                var term2 = Term.builder()
                        .startDateTime(LocalDate.now().atStartOfDay())
                        .duration(60)
                        .votes(new ArrayList<>())
                        .room(room)
                        .build();

                termRepository.saveAll(List.of(term1, term2));

                var vote = Vote.builder()
                        .voteType(VoteType.AVAILABLE)
                        .build();

                voteRepository.save(vote);

                vote.setTerm(term1);
                voteRepository.save(vote);

                term1.addVote(vote);

                termRepository.saveAll(List.of(term1, term2));

                room.addTerm(term1);
                room.addTerm(term2);

                roomRepository.save(room);
            }
        };
    }
}
