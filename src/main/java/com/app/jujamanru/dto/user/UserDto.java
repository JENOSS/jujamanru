package com.app.jujamanru.dto.user;

import com.app.jujamanru.dto.team.TeamDto;
import lombok.Builder;

public record UserDto(String id,
                      String nickName,
                      Boolean isAdmin,
                      TeamDto team,
                      String accessToken) {

    @Builder
    public UserDto {

    }
}
