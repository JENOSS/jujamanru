package com.app.jujamanru.dto.gameRecord;

import lombok.Getter;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Getter
public class GameRecordSaveRequest {
    private Long myTeamId;
    private Long opponentTeamId;
    private String gameResult;
    private String text;
    private LocalDate matchDate;
    private String userId;


}
