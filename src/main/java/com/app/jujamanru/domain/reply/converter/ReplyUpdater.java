package com.app.jujamanru.domain.reply.converter;

import com.app.jujamanru.domain.reply.model.Reply;
import com.app.jujamanru.dto.reply.ReplySaveRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReplyUpdater {
    private final Reply origin;
    private final ReplySaveRequest request;

    public Reply update() {
        return origin
                .changeText(request.getText());
    }
}
