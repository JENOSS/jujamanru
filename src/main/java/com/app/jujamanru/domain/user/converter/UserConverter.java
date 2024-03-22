package com.app.jujamanru.domain.user.converter;

import com.app.jujamanru.domain.user.model.User;
import com.app.jujamanru.dto.user.UserSaveRequest;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class UserConverter {
    private final UserSaveRequest origin;

    public User convert() {
        return User.builder()
                .id(origin.getUserId())
                .nickName(origin.getNickName())
                .isAdmin(origin.getIsAdmin())
                .createdDatetime(LocalDateTime.now())
                .build();
    }
}
