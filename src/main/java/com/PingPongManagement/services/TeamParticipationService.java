package com.PingPongManagement.services;

import java.util.List;

import com.PingPongManagement.models.PlayerParticipation;
import com.PingPongManagement.repositories.PlayerParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PingPongManagement.exceptions.AppException;
import com.PingPongManagement.models.TeamParticipation;
import com.PingPongManagement.repositories.LeagueRepository;
import com.PingPongManagement.repositories.TeamParticipationRepository;
import com.PingPongManagement.repositories.TeamRepository;

@Service
@Transactional
public class TeamParticipationService {

	@Autowired
	private TeamParticipationRepository teamParticipationRepository;

	@Autowired
	private LeagueRepository leagueRepository;

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private PlayerParticipationRepository playerParticipationRepository;

	public TeamParticipation saveTeamParticipation(TeamParticipation teamParticipation) {
		return teamParticipationRepository.save(teamParticipation);
	}

	public List<TeamParticipation> saveTeamParticipations(List<TeamParticipation> teamParticipations) {
		return teamParticipationRepository.saveAll(teamParticipations);
	}

	public List<TeamParticipation> getTeamsByLeagueId(Integer leagueId) {
		return teamParticipationRepository.findByLeagueLeagueId(leagueId);
	}

	public List<TeamParticipation> getLeaguesByTeamId(Integer teamId) {
		return teamParticipationRepository.findByTeamTeamId(teamId);
	}

	public TeamParticipation getTeamParticipationByLeagueIdAndTeamId(Integer leagueId,
																	 Integer teamId) {
		TeamParticipation teamParticipation =
				teamParticipationRepository.findByLeagueLeagueIdAndTeamTeamId(leagueId,	teamId).orElseThrow(() -> new AppException("Team Participation not found!"));

		return teamParticipation;
	}

	public List<PlayerParticipation> getPlayersParticipationByLeagueIdAndTeamId(Integer leagueId,
																			   Integer teamId) {
		TeamParticipation teamParticipation = getTeamParticipationByLeagueIdAndTeamId(leagueId, teamId);

		return playerParticipationRepository.findByTeamParticipationTeamParticipationId(teamParticipation.getTeamParticipationId());
	}

	public String deleteTeamParticipation(Integer teamParticipationId) {
		teamParticipationRepository.deleteById(teamParticipationId);
		return "Team Participation Removed!!" + teamParticipationId;
	}

	/*public TeamParticipation updateTeamParticipation(TeamParticipation teamParticipation) {
		TeamParticipation existing =
				teamParticipationRepository.findById(teamParticipation.getTeamParticipationId())
						.orElseThrow(() -> new AppException("Team Participation not found!"));
		existing.setTeam(teamParticipation.getTeam());
		existing.setLeague(teamParticipation.getLeague());
		existing.setSeries(teamParticipation.getSeries());
		existing.setGroup(teamParticipation.getGroup());
		existing.setRanking(teamParticipation.getRanking());
		existing.setPoints(teamParticipation.getPoints());
		return teamParticipationRepository.save(existing);
	}*/
}
