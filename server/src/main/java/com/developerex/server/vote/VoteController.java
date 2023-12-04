package com.developerex.server.vote;


import com.developerex.server.vote.dto.VoteDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/votes")
public class VoteController {

    private final VoteService voteService;

    @GetMapping
    public ResponseEntity<List<VoteDto>> getAllVotes() {
        List<VoteDto> votes = voteService.getAllVotes();
        return new ResponseEntity<>(votes, HttpStatus.OK);
    }
}
