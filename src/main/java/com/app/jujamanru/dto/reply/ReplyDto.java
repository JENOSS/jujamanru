package com.app.jujamanru.dto.reply;

import lombok.Builder;

import java.time.LocalDateTime;

public record ReplyDto(Long id,
                       String text,
                       Long postId,
                       String createdBy,
                       LocalDateTime modifiedDatetime,
                       Boolean isUpdated) {

    @Builder
    public ReplyDto {

    }
}
