package com.developerex.server.vote;

import com.developerex.server.term.Term;
import com.developerex.server.user.User;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Vote {
    private VoteType voteType;

    private User user;

    @Id
    private Long id;

    @ManyToOne
    private Term term;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
