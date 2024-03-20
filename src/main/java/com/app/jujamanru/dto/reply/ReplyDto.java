package com.app.jujamanru.dto.reply;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ReplyDto(Long id,
                       Long postId,
                       Long teamId,
                       String teamName,
                       String text,
                       String createdBy,
                       LocalDateTime modifiedDatetime,
                       Boolean isUpdated) {

    @Builder
    public ReplyDto {

    }

    public String getTimeView() {
        return modifiedDatetime.toLocalDate().isEqual(LocalDate.now())
                ? modifiedDatetime.toLocalTime().getHour() + ":" + modifiedDatetime.toLocalTime().getMinute()
                : modifiedDatetime.toLocalDate().toString();
    }
}
