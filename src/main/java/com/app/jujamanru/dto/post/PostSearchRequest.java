package com.app.jujamanru.dto.post;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostSearchRequest {
    private Long teamId;
    private Boolean isNotice;
    private Boolean mustRead;
    private String userId;
    private Integer page;
    private Integer size;

    @Builder
    public PostSearchRequest(Long teamId, Boolean isNotice, String userId,
                             Boolean mustRead, Integer page, Integer size) {
        this.teamId = teamId;
        this.isNotice = Objects.requireNonNullElse(isNotice, false);
        this.mustRead = Objects.requireNonNullElse(mustRead, false);
        this.userId = userId;
        this.page = Objects.requireNonNullElse(page, 0);
        this.size = Objects.requireNonNullElse(size, 20);
    }
}
