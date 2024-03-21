package com.app.jujamanru.dto.post;

import lombok.Getter;

import javax.persistence.Column;

@Getter
public class PostSaveRequest {
    private String title;
    private Long teamId;
    private Boolean isNotice;
    private Boolean mustRead;
    private String text;
    private String userId;
}
