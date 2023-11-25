package com.developerex.server.vote;

import com.developerex.server.term.Term;
import lombok.Builder;


@Builder
public record VoteDto(
        VoteType voteType,
        Term term
) {
}
