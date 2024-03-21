package com.app.jujamanru.domain.reply.converter;

import com.app.jujamanru.domain.reply.model.Reply;
import com.app.jujamanru.dto.reply.ReplySaveRequest;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class ReplyConverter {
    private final ReplySaveRequest origin;

    public Reply convert() {
        return Reply.builder()
                .postId(origin.getPostId())
                .text(origin.getText())
                .createdBy(origin.getUserId())
                .createdDatetime(LocalDateTime.now())
                .build();
    }
}
