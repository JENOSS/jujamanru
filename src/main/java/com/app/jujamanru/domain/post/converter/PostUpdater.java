package com.app.jujamanru.domain.post.converter;

import com.app.jujamanru.domain.post.model.Post;
import com.app.jujamanru.dto.post.PostSaveRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostUpdater {
    private final Post origin;
    private final PostSaveRequest request;

    public Post update() {
        return origin
                .changeTitle(request.getTitle())
                .changeText(request.getText())
                .changeIsNotice(request.getIsNotice())
                .changeMustRead(request.getMustRead())
                .changeTeamId(request.getIsNotice() || request.getMustRead() ? null : request.getTeamId());
    }
}
