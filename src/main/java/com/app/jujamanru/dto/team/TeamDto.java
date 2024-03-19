package com.app.jujamanru.dto.team;

import lombok.Builder;

public record TeamDto(Long id,
                      String name) {

    @Builder
    public TeamDto {

    }

}
