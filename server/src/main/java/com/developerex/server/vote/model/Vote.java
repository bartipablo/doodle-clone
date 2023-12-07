package com.developerex.server.vote.model;

import com.developerex.server.attendee.model.Attendee;
import com.developerex.server.term.model.Term;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Vote {
    private VoteType voteType;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="term_id")
    @JsonManagedReference
    @NotNull
    private Term term;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="attendee_id")
    @JsonManagedReference
    private Attendee attendee;

}
