package com.app.jujamanru.dto.post;

import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import java.util.Objects;

@Getter
public class PostSaveRequest {
    private String title;
    private Long teamId;
    private Boolean isNotice;
    private Boolean mustRead;
    private String text;
    private String userId;

    public Boolean getIsNotice() {
        return Objects.requireNonNullElse(isNotice, false);
    }

    public Boolean getMustRead() {
        return Objects.requireNonNullElse(mustRead, false);
    }
}
