package com.app.jujamanru.domain.post.converter;

import com.app.jujamanru.domain.post.model.Post;
import com.app.jujamanru.dto.post.PostSaveRequest;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class PostConverter {
    private final PostSaveRequest origin;

    public Post convert() {
        return Post.builder()
                .title(origin.getTitle())
                .teamId(origin.getIsNotice() || origin.getMustRead() ? null : origin.getTeamId())
                .isNotice(origin.getIsNotice())
                .mustRead(origin.getMustRead())
                .text(origin.getText())
                .viewCount(0)
                .createdBy(origin.getUserId())
                .createdDatetime(LocalDateTime.now())
                .build();
    }
}
