package com.app.jujamanru.domain.team.converter;

import com.app.jujamanru.domain.team.model.Team;
import com.app.jujamanru.dto.team.TeamDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TeamDtoConverter {
    private final Team origin;

    public TeamDto convert() {
        return TeamDto.builder()
                .id(origin.getId())
                .name(origin.getName())
                .build();
    }
}
