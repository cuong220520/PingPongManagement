package com.PingPongManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PingPongManagement.models.League;

import java.util.List;

public interface LeagueRepository extends JpaRepository<League, Integer> {
    List<League> findByLeagueNameContaining(String term);
}
