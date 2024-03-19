package com.app.jujamanru.domain.team.repository;

import com.app.jujamanru.domain.team.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
