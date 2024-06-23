package com.app.jujamanru.domain.gameRecord.converter;

import com.app.jujamanru.domain.gameRecord.model.GameResult;
import com.app.jujamanru.domain.gameRecord.model.GameRecord;
import com.app.jujamanru.dto.gameRecord.GameRecordSaveRequest;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class GameRecordConverter {
    private final GameRecordSaveRequest origin;

    public GameRecord convert() {
        return GameRecord.builder()
                .myTeamId(origin.getMyTeamId())
                .opponentTeamId(origin.getOpponentTeamId())
                .text(origin.getText())
                .matchDate(origin.getMatchDate())
                .gameResult(GameResult.valueOf(origin.getGameResult()))
                .createdBy(origin.getUserId())
                .createdDatetime(LocalDateTime.now())
                .build();
    }
}
