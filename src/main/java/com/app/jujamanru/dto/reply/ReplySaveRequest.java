package com.app.jujamanru.dto.reply;

import lombok.Getter;

@Getter
public class ReplySaveRequest {
    private Long postId;
    private String userId;
    private String text;
}
