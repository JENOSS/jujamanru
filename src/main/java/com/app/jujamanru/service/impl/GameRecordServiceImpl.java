package com.app.jujamanru.service.impl;

import com.app.jujamanru.component.exception.CustomException;
import com.app.jujamanru.domain.gameRecord.converter.GameRecordConverter;
import com.app.jujamanru.domain.gameRecord.converter.GameRecordDtoConvert;
import com.app.jujamanru.domain.gameRecord.model.GameRecordImage;
import com.app.jujamanru.domain.gameRecord.repository.GameRecordImageRepository;
import com.app.jujamanru.domain.gameRecord.repository.GameRecordRepository;
import com.app.jujamanru.domain.team.repository.TeamRepository;
import com.app.jujamanru.dto.gameRecord.GameRecordDto;
import com.app.jujamanru.dto.gameRecord.GameRecordSaveRequest;
import com.app.jujamanru.service.GameRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameRecordServiceImpl implements GameRecordService {
    private final GameRecordRepository gameRecordRepository;
    private final GameRecordImageRepository gameRecordImageRepository;
    private final TeamRepository teamRepository;

    private static final String baseDir = "src/main/resources/static/images";

    @Override
    @Transactional(readOnly = true)
    public List<GameRecordDto> getGameRecords(String userId) {
        return gameRecordRepository.getGameRecords(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public GameRecordDto getGameRecord(Long gameRecordId) {
        var gameRecord = gameRecordRepository.findById(gameRecordId)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "유효하지 않습니다. : " + gameRecordId));
        var gameRecordImages = gameRecordImageRepository.findByGameRecordId(gameRecordId).stream()
                .map(GameRecordImage::getImage)
                .toList();
        var myTeam = teamRepository.findById(gameRecord.getMyTeamId())
                .orElse(null);
        var opponentTeam = teamRepository.findById(gameRecord.getOpponentTeamId())
                .orElse(null);
        return new GameRecordDtoConvert(gameRecord, myTeam, opponentTeam, gameRecordImages)
                .convert();
    }

    @Override
    @Transactional
    public GameRecordDto save(GameRecordSaveRequest saveRequest, List<MultipartFile> images) {
        var result = new GameRecordConverter(saveRequest).convert();
        var entity = gameRecordRepository.save(result);

        if (images != null) {
            for (int i = 0 ; i < images.size() ; i++) {
                if (images.get(i).isEmpty()) continue;
                var image = this.saveImage(saveRequest.getUserId(), entity.getId(), i, images.get(i));
                var subEntity = GameRecordImage.builder()
                        .gameRecordId(entity.getId())
                        .image(image)
                        .build();
                gameRecordImageRepository.save(subEntity);
            }
        }

        return new GameRecordDtoConvert(entity ,null, null, List.of()).convert();
    }

    private String saveImage(String userId, Long gameRecordId, Integer index, MultipartFile image) {
        try {
            var imageName = makeImageName(userId, gameRecordId, index, image);
            var destination = Paths.get(baseDir, imageName);
            Files.copy(image.getInputStream(), destination);
            return imageName;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(HttpStatus.BAD_REQUEST, "이미지 저장에 실패했습니다.");
        }
    }

    private String makeImageName(String userId, Long gameRecordId, Integer index, MultipartFile image) {
        var originImageName = image.getOriginalFilename();
        var extensionIndex = originImageName != null ? originImageName.lastIndexOf('.') : -1;
        var extension = extensionIndex > 0 ? originImageName.substring(extensionIndex) : "";
        var newImageName = userId + "_" + gameRecordId + "_"  + index + "_" + System.currentTimeMillis();
        return newImageName + extension;
    }

    @Override
    @Transactional
    public void delete(Long gameRecordId) {
        var gameRecord = gameRecordRepository.findById(gameRecordId)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "유효하지 않습니다. : " + gameRecordId));
        var gameRecordImages = gameRecordImageRepository.findByGameRecordId(gameRecordId);
        for (var gameRecordImage : gameRecordImages) {
            var imageName = gameRecordImage.getImage();
            var image = Paths.get(baseDir, imageName).toFile();
            if (image.exists()) {
                image.delete();
            }
        }
        if (!gameRecordImages.isEmpty()) {
            gameRecordImageRepository.deleteByGameRecordId(gameRecordId);

        }
        gameRecordRepository.delete(gameRecord);
    }
}
