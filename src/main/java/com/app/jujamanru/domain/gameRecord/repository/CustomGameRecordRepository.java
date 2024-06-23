package com.app.jujamanru.domain.gameRecord.repository;

import com.app.jujamanru.dto.gameRecord.GameRecordDto;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CustomGameRecordRepository {
    List<GameRecordDto> getGameRecords(String userId);
}
