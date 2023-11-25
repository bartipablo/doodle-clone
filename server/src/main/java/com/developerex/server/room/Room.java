package com.developerex.server.room;

import com.developerex.server.term.Term;
import com.developerex.server.user.Attendee;
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
        private Long id;

        private String title;

        private String description;

        private LocalDateTime deadline;

        @ManyToOne
        @JoinColumn(name="attendee_id")
        private Attendee owner;

        @OneToMany(mappedBy = "room")
        private List<Term> terms;

        @ManyToMany
        private List<Attendee> participants;

}
