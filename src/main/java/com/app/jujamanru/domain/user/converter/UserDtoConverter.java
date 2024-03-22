package com.app.jujamanru.domain.user.converter;

import com.app.jujamanru.domain.team.converter.TeamDtoConverter;
import com.app.jujamanru.domain.team.model.Team;
import com.app.jujamanru.domain.user.model.User;
import com.app.jujamanru.dto.user.UserDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDtoConverter {
    private final User origin;

    public UserDto convert() {
        var builder = UserDto.builder()
                        .id(origin.getId())
                        .nickName(origin.getNickName())
                        .isAdmin(origin.getIsAdmin());

        if (origin.getTeam() != null) {
            builder.team(new TeamDtoConverter(origin.getTeam()).convert());
        }

        return builder.build();
    }

    public UserDto convertForLogin(String accessToken) {
        var builder = UserDto.builder()
                .id(origin.getId())
                .nickName(origin.getNickName())
                .isAdmin(origin.getIsAdmin())
                .accessToken(accessToken);

        if (origin.getTeam() != null) {
            builder.team(new TeamDtoConverter(origin.getTeam()).convert());
        }

        return builder.build();
    }
}
