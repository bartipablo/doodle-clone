package com.developerex.server.vote;

import org.springframework.stereotype.Service;


@Service
public class VoteMapper {
    public static VoteDto mapToDto(Vote vote) {
        return VoteDto.builder()
                .voteType(vote.getVoteType())
                .term(vote.getTerm())
                .build();
    }

    public static Vote mapToVote(VoteDto voteDto) {
        return Vote.builder()
                .voteType(voteDto.voteType())
                .term(voteDto.term())
                .build();
    }
}
