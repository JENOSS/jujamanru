package com.app.jujamanru.domain.reply.converter;

import com.app.jujamanru.domain.reply.model.Reply;
import com.app.jujamanru.dto.reply.ReplyDto;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class ReplyDtoConverter {
    private final Reply origin;

    public ReplyDto convert() {
        return ReplyDto.builder()
                .id(origin.getId())
                .text(origin.getText())
                .postId(origin.getPostId())
                .createdBy(origin.getCreatedBy())
                .modifiedDatetime(Objects.requireNonNullElse(origin.getModifiedDatetime(), origin.getCreatedDatetime()))
                .isUpdated(Objects.nonNull(origin.getModifiedDatetime()))
                .build();
    }
}
