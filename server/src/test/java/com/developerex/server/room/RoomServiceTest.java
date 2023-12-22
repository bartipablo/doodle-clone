package com.developerex.server.room;

import com.developerex.server.attendee.model.Attendee;
import com.developerex.server.room.dto.RoomInfoDto;
import com.developerex.server.room.mapper.RoomMapper;
import com.developerex.server.room.model.Room;
import com.developerex.server.term.mapper.TermMapper;
import com.developerex.server.term.model.Term;
import com.developerex.server.vote.mapper.VoteMapper;
import com.developerex.server.vote.model.Vote;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class RoomServiceTest {

    @Test
    void getAllRoomsOwnedByUserIdShouldReturnAllUserOwnedRooms() {
        //given
        var owner = Attendee.builder()
                .id(1L)
                .build();

        var ownedRoom1 = Room.builder()
                .id(1L)
                .owner(owner)
                .build();

        var ownedRoom2 = Room.builder()
                .id(2L)
                .owner(owner)
                .build();

        var ownedRoomDto1 = RoomMapper.mapToDto(ownedRoom1);
        var ownedRoomDto2 = RoomMapper.mapToDto(ownedRoom2);

        Optional<List<Room>> ownedRooms = Optional.of(List.of(ownedRoom1, ownedRoom2));
        RoomRepository repository = mock(RoomRepository.class);

        given(repository.findAllByOwnerId(1L)).willReturn(ownedRooms);

        RoomService roomService = new RoomService(repository);


        //when
        //then
        assertThat(roomService.getAllRoomsOwnedByUserId(1L), hasSize(2));
        assertThat(roomService.getAllRoomsOwnedByUserId(1L), contains(ownedRoomDto1, ownedRoomDto2));
    }


    @Test
    void whenUserHasNotOwnedRoomGetAllRoomsOwnedByUserIdShouldThrowException() {
        //given
        RoomRepository repository = mock(RoomRepository.class);

        given(repository.findAllByOwnerId(1L)).willReturn(Optional.empty());

        RoomService roomService = new RoomService(repository);

        //when
        //then
        assertThrows(EntityNotFoundException.class, () -> roomService.getAllRoomsOwnedByUserId(1L));
    }


    @Test
    void getAllRoomsParticipatedByUserIdShouldReturnAllUserParticipatedRooms() {
        //given
        var roomOwner = Attendee.builder()
                .id(1L)
                .build();

        var participant1 = Attendee.builder()
                .id(2L)
                .build();

        var participant2 = Attendee.builder()
                .id(3L)
                .build();

        var participatedRoom1 = Room.builder()
                .id(1L)
                .owner(roomOwner)
                .participants(Set.of(participant1, participant2))
                .build();

        var participatedRoom2 = Room.builder()
                .id(2L)
                .owner(roomOwner)
                .participants(Set.of(participant1, participant2))
                .build();

        var participatedRoomDto1 = RoomMapper.mapToDto(participatedRoom1);
        var participatedRoomDto2 = RoomMapper.mapToDto(participatedRoom2);

        Optional<List<Room>> participatedRooms = Optional.of(List.of(participatedRoom1, participatedRoom2));
        RoomRepository repository = mock(RoomRepository.class);

        given(repository.findAllByParticipantsId(1L)).willReturn(participatedRooms);

        RoomService roomService = new RoomService(repository);

        //when
        //then
        assertThat(roomService.getAllRoomsParticipatedByUserId(1L), hasSize(2));
        assertThat(roomService.getAllRoomsParticipatedByUserId(1L),
                contains(participatedRoomDto1, participatedRoomDto2));
    }

    @Test
    void whenUserHasNotParticipatedRoomGetAllRoomsParticipatedByUserIdShouldThrowException() {
        //given
        RoomRepository repository = mock(RoomRepository.class);

        given(repository.findAllByParticipantsId(1L)).willReturn(Optional.empty());

        RoomService roomService = new RoomService(repository);

        //when
        //then
        assertThrows(EntityNotFoundException.class, () -> roomService.getAllRoomsParticipatedByUserId(1L));
    }

    @Test
    void getRoomByIdShouldReturnRoomDto() {
        //given
        var roomOwner = Attendee.builder()
                .id(1L)
                .build();

        var room = Room.builder()
                .id(1L)
                .owner(roomOwner)
                .build();

        var roomDto = RoomMapper.mapToDto(room);

        RoomRepository repository = mock(RoomRepository.class);
        given(repository.findById(1L)).willReturn(Optional.of(room));
        RoomService roomService = new RoomService(repository);

        //when
        //then
        assertThat(roomService.getRoomById(1L), is(roomDto));
    }

    @Test
    void whenRoomDoesNotExistGetRoomByIdShouldThrowException() {
        //given
        RoomRepository repository = mock(RoomRepository.class);
        given(repository.findById(1L)).willReturn(Optional.empty());
        RoomService roomService = new RoomService(repository);

        //when
        //then
        assertThrows(EntityNotFoundException.class, () -> roomService.getRoomById(1L));
    }

    @Test
    void getRoomAttendeesShouldReturnListOfAttendees() {
        //given
        var roomOwner = Attendee.builder()
                .id(1L)
                .build();

        var participant1 = Attendee.builder()
                .id(2L)
                .build();

        var participant2 = Attendee.builder()
                .id(3L)
                .build();

        var room = Room.builder()
                .id(1L)
                .owner(roomOwner)
                .participants(Set.of(participant1, participant2))
                .build();

        var participantDto1 = RoomMapper.mapToDto(room).participants().get(0);
        var participantDto2 = RoomMapper.mapToDto(room).participants().get(1);

        RoomRepository repository = mock(RoomRepository.class);
        given(repository.findById(1L)).willReturn(Optional.of(room));
        RoomService roomService = new RoomService(repository);

        //when
        //then
        assertThat(roomService.getRoomAttendees(1L), hasSize(2));
        assertThat(roomService.getRoomAttendees(1L), contains(participantDto1, participantDto2));
    }

    @Test
    void whenRoomDoesNotExistGetRoomAttendeesShouldThrowException() {
        //given
        RoomRepository repository = mock(RoomRepository.class);
        given(repository.findById(1L)).willReturn(Optional.empty());
        RoomService roomService = new RoomService(repository);

        //when
        //then
        assertThrows(EntityNotFoundException.class, () -> roomService.getRoomAttendees(1L));
    }

    @Test
    void getRoomInfoShouldReturnRoomInfoDto() {
        //given
        var roomOwner = Attendee.builder()
                .id(1L)
                .username("owner")
                .email("example@example.com")
                .password("password")
                .build();

        var participant1 = Attendee.builder()
                .id(2L)
                .username("participant 1")
                .email("example@example.com")
                .password("password")
                .build();

        var participant2 = Attendee.builder()
                .id(3L)
                .username("participant 2")
                .email("example@example.com")
                .password("password")
                .build();

        var term1 = Term.builder()
                .id(1L)
                .votes(new ArrayList<>())
                .build();

        var term2 = Term.builder()
                .id(2L)
                .votes(Collections.emptyList())
                .build();

        var vote1 = Vote.builder()
                .id(1L)
                .attendee(participant1)
                .term(term1)
                .build();

        var vote2 = Vote.builder()
                .id(2L)
                .attendee(participant1)
                .term(term1)
                .build();

        term1.addVote(vote1);
        term1.addVote(vote2);

        var room = Room.builder()
                .id(1L)
                .owner(roomOwner)
                .participants(Set.of(participant1, participant2))
                .terms(List.of(term1, term2))
                .build();

        RoomRepository repository = mock(RoomRepository.class);
        given(repository.findById(1L)).willReturn(Optional.of(room));
        RoomService roomService = new RoomService(repository);

        //when
        RoomInfoDto roomInfoDto = roomService.getRoomInfo(1L);

        //then
        assertThat(roomInfoDto.owner(), is(RoomMapper.mapToDto(room).owner()));
        assertThat(roomInfoDto.participants(), hasSize(2));
        assertThat(roomInfoDto.participants(), contains(
                RoomMapper.mapToDto(room).participants().get(0),
                RoomMapper.mapToDto(room).participants().get(1))
        );
        assertThat(roomInfoDto.votesPerTerm(), hasEntry(TermMapper.mapToDto(term1), 2L));
        assertThat(roomInfoDto.votesPerTerm(), hasEntry(TermMapper.mapToDto(term2), 0L));
        assertThat(roomInfoDto.allVotes(), hasSize(2));
        assertThat(roomInfoDto.allVotes(), contains(
                VoteMapper.mapToDto(vote1),
                VoteMapper.mapToDto(vote2))
        );
    }

    @Test
    void whenRoomDoesNotExistGetRoomInfoShouldThrowException() {
        //given
        RoomRepository repository = mock(RoomRepository.class);
        given(repository.findById(1L)).willReturn(Optional.empty());
        RoomService roomService = new RoomService(repository);

        //when
        //then
        assertThrows(EntityNotFoundException.class, () -> roomService.getRoomInfo(1L));
    }

}