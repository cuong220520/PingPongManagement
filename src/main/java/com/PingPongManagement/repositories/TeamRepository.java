package com.PingPongManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PingPongManagement.models.Team;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Integer> {
	Optional<Team> findByTeamCode(String code);
}
