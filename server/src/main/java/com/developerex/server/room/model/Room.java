package com.developerex.server.room.model;

import com.developerex.server.term.model.Term;
import com.developerex.server.attendee.model.Attendee;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
        @JsonManagedReference
        private Attendee owner;

        @OneToMany(mappedBy = "room")
        @JsonManagedReference
        private List<Term> terms;

        @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST})
        @JoinTable(
                name = "room_attendee",
                joinColumns = @JoinColumn(name = "room_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "attendee_id", referencedColumnName = "id")
        )
        @JsonManagedReference
        private Set<Attendee> participants;

        public void addTerm(Term term) {
                this.terms.add(term);
        }

        public void addParticipant(Attendee attendee) {
                this.participants.add(attendee);
        }

}
