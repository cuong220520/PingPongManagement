package com.PingPongManagement.services;

import java.util.List;
import java.util.Optional;

import com.PingPongManagement.exceptions.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PingPongManagement.models.Team;
import com.PingPongManagement.repositories.TeamRepository;

@Service
public class TeamService {

	@Autowired
	private TeamRepository repository;
	
	public Team saveTeam(Team team) {
		return repository.save(team);
	}
	
	public List<Team> saveTeams(List<Team> teams) {
		return repository.saveAll(teams);
	}
	
	public List<Team> getTeams(){
		return repository.findAll();
	}
	
	public Optional<Team> getTeamByTeamId(Integer teamId){
		return repository.findById(teamId);
	}
	
	public Optional<Team> getTeamByTeamCode(String teamCode){
		return repository.findByTeamCode(teamCode);
	}
	
	public void deleteTeam(Integer teamId) {
		repository.deleteById(teamId);
	}
	
	public Team updateTeam(Team team) {
		Team existingTeam =
				repository.findById(team.getTeamId()).orElseThrow(() -> new AppException("Team " +
						"not found!"));
		existingTeam.setTeamName(team.getTeamName());
		existingTeam.setTeamCode(team.getTeamCode());
		existingTeam.settP(team.gettP());
		return repository.save(existingTeam);
	}
}
