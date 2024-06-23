package com.app.jujamanru.dto.gameRecord;

import lombok.Builder;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record GameRecordDto(Long id,
                            LocalDate matchDate,
                            Long myTeamId,
                            String myTeamName,
                            Long opponentTeamId,
                            String opponentTeamName,
                            String text,
                            String createdBy,
                            LocalDateTime createdDatetime,
                            List<String> images){

    @Builder
    public GameRecordDto {
    }
}
