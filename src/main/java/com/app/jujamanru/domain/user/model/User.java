package com.app.jujamanru.domain.user.model;

import com.app.jujamanru.domain.team.model.Team;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    private String id;
    private String nickName;
    private String password;
    private LocalDateTime createdDatetime;
    private Boolean isAdmin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;


    @Builder
    public User(String id, String nickName, Team team, LocalDateTime createdDatetime, Boolean isAdmin) {
        this.id = id;
        this.nickName = nickName;
        this.createdDatetime = createdDatetime;
        this.team = team;
        this.isAdmin = isAdmin;
    }

    public User changePassword(String password) {
        this.password = password;
        return this;
    }

    public User changeTeam(Team team) {
        this.team = team;
        return this;
    }
}
