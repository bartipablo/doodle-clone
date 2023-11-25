package com.developerex.server.term;

import com.developerex.server.vote.Vote;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime startDateTime;

    private int duration;

    @OneToMany(mappedBy = "term")
    private List<Vote> votes;
}
