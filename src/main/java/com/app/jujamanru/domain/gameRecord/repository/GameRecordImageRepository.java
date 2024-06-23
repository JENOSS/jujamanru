package com.app.jujamanru.domain.gameRecord.repository;

import com.app.jujamanru.domain.gameRecord.model.GameRecordImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRecordImageRepository extends JpaRepository<GameRecordImage, Long>{
    List<GameRecordImage> findByGameRecordId(Long gameRecordId);
    void deleteByGameRecordId(Long gameRecordId);
}
