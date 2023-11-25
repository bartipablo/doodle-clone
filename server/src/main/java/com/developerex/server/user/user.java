package com.developerex.server.user;

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
public class user {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String email;
    private String password;

    @ManyToMany
    private List<Room> participationRooms;

    @OneToMany
    private List<Room> ownedRooms;
}
