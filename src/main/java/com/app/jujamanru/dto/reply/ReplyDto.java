package com.app.jujamanru.dto.reply;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ReplyDto(Long id,
                       Long postId,
                       String text,
                       String createdBy,
                       LocalDateTime modifiedDatetime,
                       Boolean isUpdated) {

    @Builder
    public ReplyDto {

    }

    public String getTimeView() {
        return modifiedDatetime.toLocalDate().isEqual(LocalDate.now())
                ? (modifiedDatetime.toLocalTime().getHour() > 9 ? "" : "0") + modifiedDatetime.toLocalTime().getHour()
                + ":"
                + (modifiedDatetime.toLocalTime().getMinute() > 9 ? "" : "0") + modifiedDatetime.toLocalTime().getMinute()
                : modifiedDatetime.toLocalDate().toString().substring(5).replace("-", ".");
    }
}
