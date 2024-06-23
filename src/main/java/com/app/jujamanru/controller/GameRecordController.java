package com.app.jujamanru.controller;

import com.app.jujamanru.dto.gameRecord.GameRecordDto;
import com.app.jujamanru.dto.gameRecord.GameRecordSaveRequest;
import com.app.jujamanru.service.GameRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/game-records")
@RequiredArgsConstructor
public class GameRecordController {
    private final GameRecordService gameRecordService;

    @GetMapping("/list/{userId}")
    private ResponseEntity<List<GameRecordDto>> getGameRecords(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(gameRecordService.getGameRecords(userId));
    }

    @GetMapping("/{gameRecordId}")
    private ResponseEntity<GameRecordDto> getGameRecord(@PathVariable("gameRecordId") Long gameRecordId) {
        return ResponseEntity.ok(gameRecordService.getGameRecord(gameRecordId));
    }

    @PostMapping
    private ResponseEntity<GameRecordDto> save(@RequestPart("saveRequest") GameRecordSaveRequest saveRequest,
                                               @RequestPart("images") List<MultipartFile> images) {
        return ResponseEntity.ok(gameRecordService.save(saveRequest, images));
    }

    @DeleteMapping("/{gameRecordId}")
    private ResponseEntity<Void> delete(@PathVariable("gameRecordId") Long gameRecordId) {
        gameRecordService.delete(gameRecordId);
        return ResponseEntity.ok().build();
    }
}
