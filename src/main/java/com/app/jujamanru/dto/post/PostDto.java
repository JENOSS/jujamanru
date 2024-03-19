package com.app.jujamanru.dto.post;

import lombok.Builder;

import java.time.LocalDateTime;

public record PostDto(Long id,
                      String title,
                      String text,
                      Long teamId,
                      String teamName,
                      Integer viewCount,
                      Integer replyCount,
                      String createdBy,
                      LocalDateTime modifiedDatetime,
                      Boolean isUpdated) {

    @Builder
    public PostDto {

    }
}
