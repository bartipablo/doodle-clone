package com.developerex.server.attendee;

import com.developerex.server.room.Room;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Attendee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String email;
    private String password;

    @ManyToMany(mappedBy = "participants",cascade = { CascadeType.ALL})
    private List<Room> participationRooms;

    @OneToMany(mappedBy = "owner")
    private List<Room> ownedRooms;

    public void addParticipationRoom(Room room) {
        this.participationRooms.add(room);
        room.addParticipant(this);
    }

    public void addOwnedRoom(Room room) {
        this.ownedRooms.add(room);
    }
}
