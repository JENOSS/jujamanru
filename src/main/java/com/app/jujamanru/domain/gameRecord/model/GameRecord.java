package com.app.jujamanru.domain.gameRecord.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class GameRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate matchDate;
    @Enumerated(EnumType.STRING)
    private GameResult gameResult;
    private Long myTeamId;
    private Long opponentTeamId;
    @Column(columnDefinition="TEXT")
    private String text;
    private String createdBy;
    private LocalDateTime createdDatetime;

    @Builder
    public GameRecord(LocalDate matchDate, GameResult gameResult, Long myTeamId, Long opponentTeamId, String text, String createdBy, LocalDateTime createdDatetime) {
        this.matchDate = matchDate;
        this.gameResult = gameResult;
        this.myTeamId = myTeamId;
        this.opponentTeamId = opponentTeamId;
        this.text = text;
        this.createdBy = createdBy;
        this.createdDatetime = createdDatetime;
    }
}
