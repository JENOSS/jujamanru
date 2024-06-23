package com.app.jujamanru.domain.gameRecord.converter;

import com.app.jujamanru.domain.gameRecord.model.GameRecord;
import com.app.jujamanru.domain.team.model.Team;
import com.app.jujamanru.dto.gameRecord.GameRecordDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GameRecordDtoConvert {
    private final GameRecord origin;
    private final Team myTeam;
    private final Team opponentTeam;
    private final List<String> images;

    public GameRecordDto convert() {
        return GameRecordDto.builder()
                .id(origin.getId())
                .matchDate(origin.getMatchDate())
                .myTeamId(origin.getMyTeamId())
                .myTeamName(myTeam != null ? myTeam.getName() : "")
                .opponentTeamId(origin.getOpponentTeamId())
                .opponentTeamName(opponentTeam != null ? opponentTeam.getName() : "")
                .gameResult(origin.getGameResult().name())
                .text(origin.getText())
                .createdBy(origin.getCreatedBy())
                .createdDatetime(origin.getCreatedDatetime())
                .images(images)
                .build();
    }
}
