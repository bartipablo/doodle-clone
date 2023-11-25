package com.developerex.server.room;

import com.developerex.server.term.Term;
import com.developerex.server.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Room {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private String id;

        private String title;

        private String description;

        private LocalDateTime deadline;

        @ManyToOne
        private User owner;

        @OneToMany
        private List<Term> terms;

        @ManyToMany
        private List<User> participants;

}
