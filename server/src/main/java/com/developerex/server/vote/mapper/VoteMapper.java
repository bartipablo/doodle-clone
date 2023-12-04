package com.developerex.server.vote.mapper;

import com.developerex.server.term.mapper.TermMapper;
import com.developerex.server.vote.dto.VoteDto;
import com.developerex.server.vote.model.Vote;
import org.springframework.stereotype.Service;


@Service
public class VoteMapper {
    public static VoteDto mapToDto(Vote vote) {
        return VoteDto.builder()
                .voteType(vote.getVoteType())
                .term(TermMapper.mapToDto(vote.getTerm()))
                .build();
    }

    public static Vote mapToEntity(VoteDto voteDto) {
        return Vote.builder()
                .voteType(voteDto.voteType())
                .term(TermMapper.mapToEntity(voteDto.term()))
                .build();
    }
}

