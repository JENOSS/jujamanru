package com.app.jujamanru.dto.reply;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReplySearchRequest {
    private Long postId;
    private String userId;
    private Integer page;
    private Integer size;

    @Builder
    public ReplySearchRequest(Long postId, String userId, Integer page, Integer size) {
        this.postId = postId;
        this.userId = userId;
        this.page = Objects.requireNonNullElse(page, 0);
        this.size = Objects.requireNonNullElse(size, 10);
    }
}
