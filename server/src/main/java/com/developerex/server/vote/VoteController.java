package com.developerex.server.vote;


import com.developerex.server.room.RoomDto;
import com.developerex.server.room.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @GetMapping
    public List<VoteDto> getAllVotes() {
        return voteService.getAllVotes();
    }
}
