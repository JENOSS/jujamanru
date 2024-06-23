package com.app.jujamanru.service;

import com.app.jujamanru.dto.gameRecord.GameRecordDto;
import com.app.jujamanru.dto.gameRecord.GameRecordSaveRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GameRecordService {
    List<GameRecordDto> getGameRecords(String userId);
    GameRecordDto getGameRecord(Long gameRecordId);
    GameRecordDto save(GameRecordSaveRequest saveRequest, List<MultipartFile> images);

    void delete(Long gameRecordId);
}
