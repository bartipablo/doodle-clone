package com.developerex.server.room;

import com.developerex.server.term.Term;
import com.developerex.server.attendee.Attendee;
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
        private List<Attendee> participants;

        public void addTerm(Term term) {
                this.terms.add(term);
        }

        public void addParticipant(Attendee attendee) {

                this.participants.add(attendee);
//                attendee.addParticipationRoom(this);

        }

}
/*
02:40:57: Executing ':ServerApplication.main()'...

> Task :compileJava UP-TO-DATE
> Task :processResources UP-TO-DATE
> Task :classes UP-TO-DATE

> Task :ServerApplication.main()

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::               (v2.7.17)

2023-11-26 02:40:58.611  INFO 7534 --- [           main] c.developerex.server.ServerApplication   : Starting ServerApplication using Java 17.0.6 on MacBook-Air-Bartosz-2.local with PID 7534 (/Users/bartipablo/Projects/AGH/TO-PROJECT/pg-pn-1820-developerex/server/build/classes/java/main started by bartipablo in /Users/bartipablo/Projects/AGH/TO-PROJECT/pg-pn-1820-developerex/server)
2023-11-26 02:40:58.612  INFO 7534 --- [           main] c.developerex.server.ServerApplication   : No active profile set, falling back to 1 default profile: "default"
2023-11-26 02:40:58.831  INFO 7534 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2023-11-26 02:40:58.852  INFO 7534 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 18 ms. Found 4 JPA repository interfaces.
2023-11-26 02:40:59.058  INFO 7534 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2023-11-26 02:40:59.062  INFO 7534 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2023-11-26 02:40:59.062  INFO 7534 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.82]
2023-11-26 02:40:59.112  INFO 7534 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2023-11-26 02:40:59.112  INFO 7534 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 484 ms
2023-11-26 02:40:59.132  INFO 7534 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2023-11-26 02:40:59.228  INFO 7534 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2023-11-26 02:40:59.232  INFO 7534 --- [           main] o.s.b.a.h2.H2ConsoleAutoConfiguration    : H2 console available at '/h2-console'. Database available at 'jdbc:h2:./database'
2023-11-26 02:40:59.298  INFO 7534 --- [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2023-11-26 02:40:59.317  INFO 7534 --- [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 5.6.15.Final
2023-11-26 02:40:59.379  INFO 7534 --- [           main] o.hibernate.annotations.common.Version   : HCANN000001: Hibernate Commons Annotations {5.1.2.Final}
2023-11-26 02:40:59.423  INFO 7534 --- [           main] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.H2Dialect
Hibernate: create table attendee (id bigint not null, email varchar(255), password varchar(255), username varchar(255), primary key (id))
Hibernate: create table room (id bigint not null, deadline timestamp, description varchar(255), title varchar(255), owner_id bigint, primary key (id))
Hibernate: create table room_attendee (room_id bigint not null, attendee_id bigint not null)
Hibernate: create table term (id bigint not null, duration integer not null, start_date_time timestamp, room_id bigint, primary key (id))
Hibernate: create table vote (id bigint not null, vote_type integer, term_id bigint, primary key (id))
Hibernate: alter table room add constraint FKcn5ysxqow0mrpq7hpo2k319dj foreign key (owner_id) references attendee
Hibernate: alter table room_attendee add constraint FKqssmgjkdql65i8wulxqpcyaxg foreign key (attendee_id) references attendee
Hibernate: alter table room_attendee add constraint FKra1kv00txunv010wld80cybpp foreign key (room_id) references room
Hibernate: alter table term add constraint FKdn44hgv1cqw265da06ic9i1ec foreign key (room_id) references room
Hibernate: alter table vote add constraint FKc9h1ggfr3tkjka1tlpp189mi6 foreign key (term_id) references term
2023-11-26 02:40:59.687  INFO 7534 --- [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
2023-11-26 02:40:59.690  INFO 7534 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2023-11-26 02:40:59.827  WARN 7534 --- [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2023-11-26 02:40:59.955  INFO 7534 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2023-11-26 02:40:59.960  INFO 7534 --- [           main] c.developerex.server.ServerApplication   : Started ServerApplication in 1.474 seconds (JVM running for 1.679)
Hibernate: select count(*) as col_0_0_ from room room0_
Hibernate: call next value for hibernate_sequence
Hibernate: call next value for hibernate_sequence
Hibernate: call next value for hibernate_sequence
Hibernate: insert into attendee (email, password, username, id) values (?, ?, ?, ?)
Hibernate: insert into attendee (email, password, username, id) values (?, ?, ?, ?)
Hibernate: insert into attendee (email, password, username, id) values (?, ?, ?, ?)
Hibernate: call next value for hibernate_sequence
Hibernate: insert into room (deadline, description, owner_id, title, id) values (?, ?, ?, ?, ?)
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaJEBAC PEDALOW
1
Hibernate: select attendee0_.id as id1_0_0_, attendee0_.email as email2_0_0_, attendee0_.password as password3_0_0_, attendee0_.username as username4_0_0_ from attendee attendee0_ where attendee0_.id=?
Hibernate: select room0_.id as id1_1_0_, room0_.deadline as deadline2_1_0_, room0_.description as descript3_1_0_, room0_.owner_id as owner_id5_1_0_, room0_.title as title4_1_0_, attendee1_.id as id1_0_1_, attendee1_.email as email2_0_1_, attendee1_.password as password3_0_1_, attendee1_.username as username4_0_1_ from room room0_ left outer join attendee attendee1_ on room0_.owner_id=attendee1_.id where room0_.id=?
Hibernate: select room0_.id as id1_1_1_, room0_.deadline as deadline2_1_1_, room0_.description as descript3_1_1_, room0_.owner_id as owner_id5_1_1_, room0_.title as title4_1_1_, participan1_.room_id as room_id1_2_3_, attendee2_.id as attendee2_2_3_, attendee2_.id as id1_0_0_, attendee2_.email as email2_0_0_, attendee2_.password as password3_0_0_, attendee2_.username as username4_0_0_ from room room0_ left outer join room_attendee participan1_ on room0_.id=participan1_.room_id left outer join attendee attendee2_ on participan1_.attendee_id=attendee2_.id where room0_.id=?
Hibernate: select attendee0_.id as id1_0_0_, attendee0_.email as email2_0_0_, attendee0_.password as password3_0_0_, attendee0_.username as username4_0_0_ from attendee attendee0_ where attendee0_.id=?
Hibernate: select attendee0_.id as id1_0_0_, attendee0_.email as email2_0_0_, attendee0_.password as password3_0_0_, attendee0_.username as username4_0_0_ from attendee attendee0_ where attendee0_.id=?
Hibernate: insert into room_attendee (room_id, attendee_id) values (?, ?)
Hibernate: call next value for hibernate_sequence
Hibernate: call next value for hibernate_sequence
Hibernate: insert into term (duration, room_id, start_date_time, id) values (?, ?, ?, ?)
Hibernate: insert into term (duration, room_id, start_date_time, id) values (?, ?, ?, ?)
Hibernate: call next value for hibernate_sequence
Hibernate: insert into vote (term_id, vote_type, id) values (?, ?, ?)
Hibernate: select vote0_.id as id1_4_1_, vote0_.term_id as term_id3_4_1_, vote0_.vote_type as vote_typ2_4_1_, term1_.id as id1_3_0_, term1_.duration as duration2_3_0_, term1_.room_id as room_id4_3_0_, term1_.start_date_time as start_da3_3_0_ from vote vote0_ left outer join term term1_ on vote0_.term_id=term1_.id where vote0_.id=?
Hibernate: select term0_.id as id1_3_0_, term0_.duration as duration2_3_0_, term0_.room_id as room_id4_3_0_, term0_.start_date_time as start_da3_3_0_ from term term0_ where term0_.id=?
Hibernate: select room0_.id as id1_1_1_, room0_.deadline as deadline2_1_1_, room0_.description as descript3_1_1_, room0_.owner_id as owner_id5_1_1_, room0_.title as title4_1_1_, participan1_.room_id as room_id1_2_3_, attendee2_.id as attendee2_2_3_, attendee2_.id as id1_0_0_, attendee2_.email as email2_0_0_, attendee2_.password as password3_0_0_, attendee2_.username as username4_0_0_ from room room0_ left outer join room_attendee participan1_ on room0_.id=participan1_.room_id left outer join attendee attendee2_ on participan1_.attendee_id=attendee2_.id where room0_.id=?
Hibernate: select attendee0_.id as id1_0_0_, attendee0_.email as email2_0_0_, attendee0_.password as password3_0_0_, attendee0_.username as username4_0_0_ from attendee attendee0_ where attendee0_.id=?
Hibernate: update vote set term_id=?, vote_type=? where id=?
Hibernate: select term0_.id as id1_3_0_, term0_.duration as duration2_3_0_, term0_.room_id as room_id4_3_0_, term0_.start_date_time as start_da3_3_0_ from term term0_ where term0_.id=?
Hibernate: select room0_.id as id1_1_1_, room0_.deadline as deadline2_1_1_, room0_.description as descript3_1_1_, room0_.owner_id as owner_id5_1_1_, room0_.title as title4_1_1_, participan1_.room_id as room_id1_2_3_, attendee2_.id as attendee2_2_3_, attendee2_.id as id1_0_0_, attendee2_.email as email2_0_0_, attendee2_.password as password3_0_0_, attendee2_.username as username4_0_0_ from room room0_ left outer join room_attendee participan1_ on room0_.id=participan1_.room_id left outer join attendee attendee2_ on participan1_.attendee_id=attendee2_.id where room0_.id=?
Hibernate: select attendee0_.id as id1_0_0_, attendee0_.email as email2_0_0_, attendee0_.password as password3_0_0_, attendee0_.username as username4_0_0_ from attendee attendee0_ where attendee0_.id=?
Hibernate: select vote0_.id as id1_4_0_, vote0_.term_id as term_id3_4_0_, vote0_.vote_type as vote_typ2_4_0_, term1_.id as id1_3_1_, term1_.duration as duration2_3_1_, term1_.room_id as room_id4_3_1_, term1_.start_date_time as start_da3_3_1_, room2_.id as id1_1_2_, room2_.deadline as deadline2_1_2_, room2_.description as descript3_1_2_, room2_.owner_id as owner_id5_1_2_, room2_.title as title4_1_2_, attendee3_.id as id1_0_3_, attendee3_.email as email2_0_3_, attendee3_.password as password3_0_3_, attendee3_.username as username4_0_3_ from vote vote0_ left outer join term term1_ on vote0_.term_id=term1_.id left outer join room room2_ on term1_.room_id=room2_.id left outer join attendee attendee3_ on room2_.owner_id=attendee3_.id where vote0_.id=?
Hibernate: select term0_.id as id1_3_0_, term0_.duration as duration2_3_0_, term0_.room_id as room_id4_3_0_, term0_.start_date_time as start_da3_3_0_ from term term0_ where term0_.id=?
Hibernate: select room0_.id as id1_1_1_, room0_.deadline as deadline2_1_1_, room0_.description as descript3_1_1_, room0_.owner_id as owner_id5_1_1_, room0_.title as title4_1_1_, participan1_.room_id as room_id1_2_3_, attendee2_.id as attendee2_2_3_, attendee2_.id as id1_0_0_, attendee2_.email as email2_0_0_, attendee2_.password as password3_0_0_, attendee2_.username as username4_0_0_ from room room0_ left outer join room_attendee participan1_ on room0_.id=participan1_.room_id left outer join attendee attendee2_ on participan1_.attendee_id=attendee2_.id where room0_.id=?
Hibernate: select attendee0_.id as id1_0_0_, attendee0_.email as email2_0_0_, attendee0_.password as password3_0_0_, attendee0_.username as username4_0_0_ from attendee attendee0_ where attendee0_.id=?
Hibernate: select term0_.id as id1_3_0_, term0_.duration as duration2_3_0_, term0_.room_id as room_id4_3_0_, term0_.start_date_time as start_da3_3_0_, room1_.id as id1_1_1_, room1_.deadline as deadline2_1_1_, room1_.description as descript3_1_1_, room1_.owner_id as owner_id5_1_1_, room1_.title as title4_1_1_, attendee2_.id as id1_0_2_, attendee2_.email as email2_0_2_, attendee2_.password as password3_0_2_, attendee2_.username as username4_0_2_ from term term0_ left outer join room room1_ on term0_.room_id=room1_.id left outer join attendee attendee2_ on room1_.owner_id=attendee2_.id where term0_.id=?
Hibernate: select term0_.id as id1_3_0_, term0_.duration as duration2_3_0_, term0_.room_id as room_id4_3_0_, term0_.start_date_time as start_da3_3_0_, room1_.id as id1_1_1_, room1_.deadline as deadline2_1_1_, room1_.description as descript3_1_1_, room1_.owner_id as owner_id5_1_1_, room1_.title as title4_1_1_, attendee2_.id as id1_0_2_, attendee2_.email as email2_0_2_, attendee2_.password as password3_0_2_, attendee2_.username as username4_0_2_ from term term0_ left outer join room room1_ on term0_.room_id=room1_.id left outer join attendee attendee2_ on room1_.owner_id=attendee2_.id where term0_.id=?
Hibernate: insert into room_attendee (room_id, attendee_id) values (?, ?)

 */