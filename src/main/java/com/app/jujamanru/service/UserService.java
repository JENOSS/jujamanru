package com.app.jujamanru.service;

import com.app.jujamanru.dto.user.LoginRequest;
import com.app.jujamanru.dto.user.UserDto;
import com.app.jujamanru.dto.user.UserSaveRequest;

public interface UserService {
    UserDto getUser(String userId);

    UserDto login(LoginRequest request);

    UserDto signup(UserSaveRequest request);

    Long changeTeam(String userId, Long teamId);
}
