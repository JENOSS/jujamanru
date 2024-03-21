package com.app.jujamanru.service.impl;

import com.app.jujamanru.domain.team.converter.TeamDtoConverter;
import com.app.jujamanru.domain.team.repository.TeamRepository;
import com.app.jujamanru.dto.team.TeamDto;
import com.app.jujamanru.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Override
    public List<TeamDto> getTeams() {
        return teamRepository.findAllByOrderByIdDesc().stream()
                .map(TeamDtoConverter::new)
                .map(TeamDtoConverter::convert)
                .toList();
    }
}
