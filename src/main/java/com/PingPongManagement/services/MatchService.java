package com.PingPongManagement.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PingPongManagement.exceptions.AppException;
import com.PingPongManagement.models.Match;
import com.PingPongManagement.models.TeamParticipation;
import com.PingPongManagement.repositories.MatchRepository;
import com.PingPongManagement.repositories.TeamParticipationRepository;

@Service
public class MatchService {

	@Autowired
	private MatchRepository repository;

	@Autowired
	private TeamParticipationRepository teamRepo;

	public List<Match> findMatchesByTeamId(Integer teamId) {
		List<TeamParticipation> teamParticipations = teamRepo.findByTeamTeamId(teamId);

		for (int i = 0; i < teamParticipations.size(); i++) {
			System.out.println(teamParticipations.get(i));
		}

		List<Match> matches = new ArrayList<Match>();

		for (int i = 0; i < teamParticipations.size(); i++) {
			List<Match> matchesHomeThatYear = repository
					.findByHomeTeamParticipationId(teamParticipations.get(i).getTeamParticipationId());
			List<Match> matchesAwayThatYear = repository
					.findByAwayTeamParticipationId(teamParticipations.get(i).getTeamParticipationId());

			matches = Stream.concat(matches.stream(), matchesHomeThatYear.stream()).collect(Collectors.toList());

			matches = Stream.concat(matches.stream(), matchesAwayThatYear.stream()).collect(Collectors.toList());
		}
		return matches;
	}

	public List<Match> findMatchesByLeagueId(Integer leagueId) {
		List<TeamParticipation> teamParticipations = teamRepo.findByLeagueLeagueId(leagueId);

		List<Match> matches = new ArrayList<Match>();

		for (int i = 0; i < teamParticipations.size(); i++) {
			List<Match> matchesHomeThatYear = repository
					.findByHomeTeamParticipationId(teamParticipations.get(i).getTeamParticipationId());
			List<Match> matchesAwayThatYear = repository
					.findByAwayTeamParticipationId(teamParticipations.get(i).getTeamParticipationId());

			matches = Stream.concat(matches.stream(), matchesHomeThatYear.stream()).collect(Collectors.toList());

			matches = Stream.concat(matches.stream(), matchesAwayThatYear.stream()).collect(Collectors.toList());
		}

		HashSet<Match> set = new HashSet<Match>();
		set.addAll(matches);

		List<Match> result = new ArrayList<>(set);
		return result;
	}

	public Match saveMatch(Match match) {
		return repository.save(match);
	}

	public List<Match> saveMatches(List<Match> matches) {
		return repository.saveAll(matches);
	}

	public Match getMatchById(Integer matchId) {
		return repository.findById(matchId).orElseThrow(() -> new AppException("Match Not Found"));
	}

	public void deleteMatchById(Integer matchId) {
		repository.deleteById(matchId);
	}

	private static String[] makeMatches(int n) {
		int length = 0;
		if (n == 0) {
			return null;
		} else if (n % 2 == 0) {
			length = n - 1;
			String[] res = new String[length];
			int[] firstHalf = new int[n / 2];
			int[] secondHalf = new int[n / 2];
			for (int i = 0; i < n; i++) {
				if (i < n / 2) {
					firstHalf[i] = i + 1;
				} else {
					secondHalf[i - n / 2] = i + 1;
				}
				if (i < length) {
					res[i] = "";
				}
			}
			for (int i = 0; i < length; i++) {
				for (int j = 0; j < n / 2; j++) {
					res[i] += Integer.toString(firstHalf[j]) + Integer.toString(secondHalf[j]);
				}
				int temp1 = firstHalf[n / 2 - 1];
				int temp2 = secondHalf[0];
				for (int j = n / 2 - 1; j >= 1; j--) {

					firstHalf[j] = firstHalf[j - 1];
					secondHalf[n / 2 - 1 - j] = secondHalf[n / 2 - j];
				}
				firstHalf[1] = temp2;
				secondHalf[n / 2 - 1] = temp1;
			}
			return res;
		} else {
			length = n;
			String[] res = new String[length];
			int[] firstHalf = new int[n / 2 + 1];
			int[] secondHalf = new int[n / 2 + 1];
			for (int i = 0; i <= n; i++) {
				if (i < n / 2) {
					firstHalf[i] = i + 1;
				} else if (i == n / 2) {
					firstHalf[i] = 0;
				} else {
					secondHalf[i - (n + 1) / 2] = i;
				}
				if (i < length) {
					res[i] = "";
				}
			}
			for (int i = 0; i < length; i++) {
				for (int j = 0; j <= n / 2; j++) {
					res[i] += Integer.toString(firstHalf[j]) + Integer.toString(secondHalf[j]);
				}
				int temp1 = firstHalf[n / 2];
				int temp2 = secondHalf[0];
				for (int j = n / 2; j >= 1; j--) {
					firstHalf[j] = firstHalf[j - 1];
					secondHalf[n / 2 - j] = secondHalf[n / 2 - j + 1];
				}
				firstHalf[1] = temp2;
				secondHalf[n / 2] = temp1;
			}
			return res;
		}
	}

	private static String[] chooseHome(String[] matches) {
		String[] res = new String[matches.length];
		for (int i = 0; i < matches.length; i++) {
			res[i] = "";
		}
		for (int i = 0; i < matches[0].length(); i += 2) {
			if (matches[0].charAt(i) == '0') {
				continue;
			} else {
				res[0] += matches[0].charAt(i);
			}

		}
//		System.out.println(res[0]);
		for (int i = 1; i < matches.length; i++) {

			for (int j = 0; j < matches[i].length(); j += 2) {

				StringBuilder sb = new StringBuilder();
				sb.append(matches[i].charAt(j));
				sb.append(matches[i].charAt(j + 1));
				String pair = sb.toString();
				if (pair.indexOf('0') != -1) {
					continue;
				} else {
					int c0 = 0;
					int c1 = 0;
//					 System.out.println(pair.charAt(0));
//					 System.out.println(pair.charAt(1));
//					 System.out.println(res[i-1].indexOf(pair.charAt(0)) == -1);
//					 System.out.println(res[i-1].indexOf(pair.charAt(1)) == -1);
					if (res[i - 1].indexOf(pair.charAt(0)) == -1 && res[i - 1].indexOf(pair.charAt(1)) != -1) {
						res[i] += pair.charAt(0);
					} else if (res[i - 1].indexOf(pair.charAt(1)) == -1 && res[i - 1].indexOf(pair.charAt(0)) != -1) {
						res[i] += pair.charAt(1);
					} else if (res[i - 1].indexOf(pair.charAt(0)) == -1 && res[i - 1].indexOf(pair.charAt(1)) == -1) {
						for (int l = i - 1; l >= 0; l--) {
							if (res[l].indexOf(pair.charAt(0)) == -1) {
								continue;
							} else {
								if (res[l].indexOf(pair.charAt(0)) != -1) {
									c0++;
								} else {
									break;
								}
							}
						}
						for (int l = i - 1; l >= 0; l--) {
							if (res[l].indexOf(pair.charAt(1)) == -1) {
								continue;
							} else {
								if (res[l].indexOf(pair.charAt(1)) != -1) {
									c1++;
								} else {
									break;
								}
							}
						}
						if (c0 > c1) {
							res[i] += pair.charAt(1);
						} else if (c1 > c0) {
							res[i] += pair.charAt(0);
						} else {
							if (Math.random() >= 0.5) {
								res[i] += pair.charAt(1);
							} else {
								res[i] += pair.charAt(0);
							}
						}
					} else {
						for (int l = i - 1; l >= 0; l--) {
							if (res[l].indexOf(pair.charAt(0)) != -1) {
								c0++;
							} else {
								break;
							}
						}
						for (int l = i - 1; l >= 0; l--) {
							if (res[l].indexOf(pair.charAt(1)) != -1) {
								c1++;
							} else {
								break;
							}
						}
						if (c0 > c1) {
							res[i] += pair.charAt(1);
						} else if (c1 > c0) {
							res[i] += pair.charAt(0);
						} else {
							if (Math.random() >= 0.5) {
								res[i] += pair.charAt(1);
							} else {
								res[i] += pair.charAt(0);
							}
						}
					}
				}
			}
		}
		return res;
	}

	public List<Match> generateSchedule(List<TeamParticipation> teams) {
		int length = teams.size();
		String[] matches = makeMatches(length);
		String[] homeTeams = chooseHome(matches);
		int days = matches.length;

		for (int i = 0; i < days; i++) {
			for (int j = 0; j < matches[i].length(); j += 2) {
				if (matches[i].charAt(j) == '0' || matches[i].charAt(j + 1) == '0') {
					StringBuilder sb = new StringBuilder(matches[i]);
					sb.deleteCharAt(j);
					sb.deleteCharAt(j + 1);
					matches[i] = sb.toString();
				}
			}
		}
		List<Match> schedule = new ArrayList<Match>();

		for (int i = 0; i < days; i++) {
			for (int j = 0; j < homeTeams[i].length(); j++) {
				Match matchFirst = new Match();
				Match matchSecond = new Match();
				if (homeTeams[i].charAt(j) == matches[i].charAt(2 * j)) {
					int home = Character.getNumericValue(matches[i].charAt(2 * j)) - 1;
					int away = Character.getNumericValue(matches[i].charAt(2 * j + 1)) - 1;
					matchFirst.setHome(teams.get(home));
					matchFirst.setAway(teams.get(away));
					matchSecond.setHome(teams.get(away));
					matchSecond.setAway(teams.get(home));
				} else if (homeTeams[i].charAt(j) == matches[i].charAt(2 * j + 1)) {
					int home = Character.getNumericValue(matches[i].charAt(2 * j + 1)) - 1;
					int away = Character.getNumericValue(matches[i].charAt(2 * j)) - 1;
					matchFirst.setHome(teams.get(home));
					matchFirst.setAway(teams.get(away));
					matchSecond.setHome(teams.get(away));
					matchSecond.setAway(teams.get(home));
				}
				int round = i + 1;
				matchFirst.setRound("D" + round);
				matchSecond.setRound("V" + round);
				schedule.add(matchFirst);
				schedule.add(matchSecond);
			}
		}
		return repository.saveAll(schedule);
	}

}
