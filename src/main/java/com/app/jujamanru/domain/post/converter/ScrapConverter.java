package com.app.jujamanru.domain.post.converter;

import com.app.jujamanru.domain.post.model.Scrap;
import com.app.jujamanru.dto.post.ScrapSaveRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ScrapConverter {
    private final ScrapSaveRequest origin;

    public Scrap convert() {
        return Scrap.builder()
                .postId(origin.getPostId())
                .userId(origin.getUserId())
                .build();
    }
}
