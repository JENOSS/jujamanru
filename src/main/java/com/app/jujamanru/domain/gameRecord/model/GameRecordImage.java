package com.app.jujamanru.domain.gameRecord.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class GameRecordImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long gameRecordId;
    private String image;
    private String createdBy;
    private LocalDateTime createdDatetime;

    @Builder
    public GameRecordImage(Long gameRecordId,
                           String image,
                           String createdBy,
                           LocalDateTime createdDatetime) {
        this.gameRecordId = gameRecordId;
        this.image = image;
        this.createdBy = createdBy;
        this.createdDatetime = createdDatetime;
    }

    public static GameRecordImage of(Long gameRecordId, String image, String createdBy) {
        return GameRecordImage.builder()
                .gameRecordId(gameRecordId)
                .image(image)
                .createdBy(createdBy)
                .createdDatetime(LocalDateTime.now())
                .build();
    }
}
