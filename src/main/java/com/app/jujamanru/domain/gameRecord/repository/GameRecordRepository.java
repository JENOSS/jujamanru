package com.app.jujamanru.domain.gameRecord.repository;

import com.app.jujamanru.domain.gameRecord.model.GameRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRecordRepository extends JpaRepository<GameRecord, Long>, CustomGameRecordRepository{
    List<GameRecord> findByCreatedBy(String userId);
}
