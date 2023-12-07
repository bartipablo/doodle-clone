package com.developerex.server.vote;

import com.developerex.server.attendee.AttendeeRepository;
import com.developerex.server.room.RoomRepository;
import com.developerex.server.room.model.Room;
import com.developerex.server.term.TermRepository;
import com.developerex.server.vote.dto.NewVoteDto;
import com.developerex.server.vote.dto.VoteDto;
import com.developerex.server.vote.mapper.VoteMapper;
import com.developerex.server.vote.model.Vote;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final RoomRepository roomRepository;
    private final TermRepository termRepository;
    private final AttendeeRepository attendeeRepository;

    public List<VoteDto> getAllVotes() {
        return voteRepository.findAll()
                .stream()
                .map(VoteMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public boolean addVote(NewVoteDto voteDto) {
        LocalDateTime term = termRepository.findById(voteDto.getTermId()).orElseThrow(() -> new EntityNotFoundException("No term found with id: " + voteDto.getTermId())).getStartDateTime();

        if (term.isBefore(LocalDateTime.now())) {
            return false;
        }

        Vote vote = Vote.builder().voteType(voteDto.getVoteType())
                .attendee(attendeeRepository.findById(voteDto.getAttendeeId()).orElseThrow(() -> new EntityNotFoundException("No attendee found with id: " + voteDto.getAttendeeId())))
                .term(termRepository.findById(voteDto.getTermId()).orElseThrow(() -> new EntityNotFoundException("No term found with id: " + voteDto.getTermId()))).build();

        voteRepository.save(vote);

        return true;
    }

    public boolean stopVoting(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new EntityNotFoundException("No room found with id: " + roomId));
        room.setDeadline(LocalDateTime.now());
        roomRepository.save(room);
        return true;
    }
}
