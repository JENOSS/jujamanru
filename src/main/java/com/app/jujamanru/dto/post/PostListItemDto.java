package com.app.jujamanru.dto.post;

import com.app.jujamanru.dto.reply.ReplyDto;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record PostListItemDto(Long id,
                              String title,
                              Long teamId,
                              String teamName,
                              Integer viewCount,
                              Long replyCount,
                              String createdBy,
                              LocalDateTime modifiedDatetime,
                              Boolean isNotice,
                              Boolean mustRead) {

    @Builder
    public PostListItemDto {

    }

    public String getTimeView() {
        return modifiedDatetime.toLocalDate().isEqual(LocalDate.now())
                ? (modifiedDatetime.toLocalTime().getHour() > 9 ? "" : "0") + modifiedDatetime.toLocalTime().getHour()
                + ":"
                + (modifiedDatetime.toLocalTime().getMinute() > 9 ? "" : "0") + modifiedDatetime.toLocalTime().getMinute()
                : modifiedDatetime.toLocalDate().toString().substring(5).replace("-", ".");
    }
}
