package com.PingPongManagement.services;

import com.PingPongManagement.dtos.SearchRequest;
import com.PingPongManagement.exceptions.AppException;
import com.PingPongManagement.models.Player;
import com.PingPongManagement.models.PlayerAchievement;
import com.PingPongManagement.repositories.PlayerAchievementRepository;
import com.PingPongManagement.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerAchievementRepository playerAchievementRepository;

    // get all players service
    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    // search players service
    public List<Player> searchPlayers(SearchRequest term) {
        return playerRepository.findByFirstNameContaining(term.getTerm());
    }

    // save player service
    public void savePlayer(Player player) {
        playerRepository.save(player);
    }

    // get player by playerId service
    public Optional<Player> getPlayer(Integer playerId) {
        return playerRepository.findById(playerId);
    }

    // delete player by playerId service
    public void deletePlayer(Integer playerId) {
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new AppException(
                "No player found with playerId: " + playerId));

        playerRepository.delete(player);
    }

    // update player achievement
    public void updatePlayerAchievement(Integer playerId) {
        Player player = getPlayer(playerId).orElseThrow(() -> new AppException("Player not found " +
                "with playerId: " + playerId));

        if (player.getAccumulatedPoint() != 0.0) {
            PlayerAchievement playerAchievement = new PlayerAchievement();

            player.setUpdatedPoint(player.getUpdatedPoint() + player.getAccumulatedPoint());

            player.setAccumulatedPoint(0.0);

            Double playerUpdatedPoint = player.getUpdatedPoint();

            if (playerUpdatedPoint > 900.0 && playerUpdatedPoint < 1099.0) {
                player.setRanking("F");
                playerAchievement.setRanking("F");
            } else if (playerUpdatedPoint > 1100.0 && playerUpdatedPoint < 1299.0) {
                player.setRanking("E");
                playerAchievement.setRanking("E");
            } else if (playerUpdatedPoint > 1300.0 && playerUpdatedPoint < 1499.0) {
                player.setRanking("D");
                playerAchievement.setRanking("D");
            } else if (playerUpdatedPoint > 1500.0 && playerUpdatedPoint < 1699.0) {
                player.setRanking("C");
                playerAchievement.setRanking("C");
            } else if (playerUpdatedPoint > 1700.0 && playerUpdatedPoint < 1899.0) {
                player.setRanking("B");
                playerAchievement.setRanking("B");
            } else {
                player.setRanking("Unknown");
                playerAchievement.setRanking("Unknown");
            }

            playerAchievement.setPlayer(player);
            playerAchievement.setNickName(player.getNickName());
            playerAchievement.setPlayerCode(player.getPlayerCode());
            playerAchievement.setPoint(playerUpdatedPoint);
            playerAchievement.setUpdatedDate(new Date());

            playerRepository.save(player);
            playerAchievementRepository.save(playerAchievement);
        }
    }

    // update all players achievement
    public void updatePlayersAchievement() {
        List<Player> players = getPlayers();

        if (!players.isEmpty()) {
            for (Player player : players) {
                updatePlayerAchievement(player.getPlayerId());
            }
        }
    }

    // get all player achievement
    public List<PlayerAchievement> getPlayerAchievements(Integer playerId) {
        return playerAchievementRepository.findByPlayerPlayerId(playerId);
    }
}
