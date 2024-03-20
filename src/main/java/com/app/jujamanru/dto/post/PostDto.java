package com.app.jujamanru.dto.post;

import com.app.jujamanru.dto.reply.ReplyDto;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public record PostDto(Long id,
                      String title,
                      String text,
                      Long teamId,
                      String teamName,
                      String timeView,
                      Integer viewCount,
                      Integer replyCount,
                      String createdBy,
                      LocalDateTime modifiedDatetime,
                      Boolean idUpdated,
                      Boolean isNotice,
                      Boolean mustRead,
                      List<ReplyDto> replies) {

    @Builder
    public PostDto {

    }
}
