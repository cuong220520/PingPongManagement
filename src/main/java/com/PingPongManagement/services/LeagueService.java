package com.PingPongManagement.services;

import java.util.List;
import java.util.Optional;

import com.PingPongManagement.dtos.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PingPongManagement.models.League;
import com.PingPongManagement.repositories.LeagueRepository;

@Service
public class LeagueService {

	@Autowired
	private LeagueRepository leagueRepository;
	
	public League saveLeague(League league) {
		return leagueRepository.save(league);
	}
	
	public List<League> saveLeagues(List<League> leagues) {
		return leagueRepository.saveAll(leagues);
	}
	
	public List<League> getLeagues() {
		return leagueRepository.findAll();
	}
	
	public Optional<League> getLeague(Integer leagueId) {
		/*return leagueRepository.findById(id).orElseThrow(() -> new AppException("Player not " +
				"found!"));*/

		return leagueRepository.findById(leagueId);
	}

	public List<League> searchLeagues(SearchRequest searchRequest) {
		return leagueRepository.findByLeagueNameContaining(searchRequest.getTerm());
	}
	
	public void deleteLeague(Integer leagueId) {
		leagueRepository.deleteById(leagueId);
	}
	
	/*public League updateLeague(League league) {
		League existingLeague = leagueRepository.findById(league.getLeagueId()).orElse(null);
		existingLeague.setLeagueName(league.getLeagueName());
		existingLeague.setStartDate(league.getStartDate());
		existingLeague.setEndDate(league.getEndDate());
		return leagueRepository.save(existingLeague);
	}*/
}
