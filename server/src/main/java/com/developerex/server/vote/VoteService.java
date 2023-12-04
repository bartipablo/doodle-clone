package com.developerex.server.vote;

import com.developerex.server.vote.dto.VoteDto;
import com.developerex.server.vote.mapper.VoteMapper;
import com.developerex.server.vote.model.Vote;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;

    public List<VoteDto> getAllVotes() {
        return voteRepository.findAll()
                .stream()
                .map(VoteMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public boolean addVote(VoteDto voteDto) {
        Vote vote = VoteMapper.mapToEntity(voteDto);
        voteRepository.save(vote);
        return true;
    }

}
