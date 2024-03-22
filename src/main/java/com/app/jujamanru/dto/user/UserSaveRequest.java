package com.app.jujamanru.dto.user;

import lombok.Getter;

@Getter
public class UserSaveRequest {
    private String userId;
    private String nickName;
    private String password;
    private Boolean isAdmin;
    private Long teamId;
}
