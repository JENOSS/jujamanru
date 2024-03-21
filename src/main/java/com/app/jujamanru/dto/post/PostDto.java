package com.app.jujamanru.dto.post;

import com.app.jujamanru.dto.reply.ReplyDto;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record PostDto(Long id,
                      String title,
                      String text,
                      Long teamId,
                      String teamName,
                      Integer viewCount,
                      Long replyCount,
                      String createdBy,
                      LocalDateTime modifiedDatetime,
                      Boolean isUpdated,
                      Boolean isNotice,
                      Boolean mustRead,
                      List<ReplyDto> replies) {

    @Builder
    public PostDto {

    }

    public String getTimeView() {
        return modifiedDatetime.toLocalDate().isEqual(LocalDate.now())
                ? (modifiedDatetime.toLocalTime().getHour() > 9 ? "" : "0") + modifiedDatetime.toLocalTime().getHour()
                + ":"
                + (modifiedDatetime.toLocalTime().getMinute() > 9 ? "" : "0") + modifiedDatetime.toLocalTime().getMinute()
                : modifiedDatetime.toLocalDate().toString().substring(5).replace("-", ".");
    }
}
