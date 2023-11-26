package com.developerex.server.room;

import com.developerex.server.term.Term;
import com.developerex.server.attendee.Attendee;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Room {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String title;

        private String description;

        private LocalDateTime deadline;

        @ManyToOne
        @JoinColumn(name="owner_id")
        private Attendee owner;

        @OneToMany(mappedBy = "room")
        private List<Term> terms;

        @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL})
        @JoinTable(
                name = "room_attendee",
                joinColumns = @JoinColumn(name = "room_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "attendee_id", referencedColumnName = "id")
        )
        private Set<Attendee> participants;

        public void addTerm(Term term) {
                this.terms.add(term);
        }

        public void addParticipant(Attendee attendee) {
                this.participants.add(attendee);
        }

}
