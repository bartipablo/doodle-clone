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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;

    @ManyToMany
    private List<Room> participationRooms;

    @OneToMany(mappedBy = "owner")
    private List<Room> ownedRooms;
}
