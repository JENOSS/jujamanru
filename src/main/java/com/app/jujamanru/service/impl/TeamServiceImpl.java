package com.app.jujamanru.service.impl;

import com.app.jujamanru.domain.team.converter.TeamDtoConverter;
import com.app.jujamanru.domain.team.repository.TeamRepository;
import com.app.jujamanru.dto.team.TeamDto;
import com.app.jujamanru.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TeamDto> getTeams() {
        return teamRepository.findAllByOrderByIdAsc().stream()
                .map(TeamDtoConverter::new)
                .map(TeamDtoConverter::convert)
                .toList();
    }
}
