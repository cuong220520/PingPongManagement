package com.PingPongManagement.services;

import com.PingPongManagement.dtos.SearchRequest;
import com.PingPongManagement.exceptions.AppException;
import com.PingPongManagement.models.Player;
import com.PingPongManagement.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    public List<Player> searchPlayers(SearchRequest term) {
        return playerRepository.findByFirstNameContaining(term.getTerm());
    }

    public void savePlayer(Player player) {
        playerRepository.save(player);
    }

    public Optional<Player> getPlayer(Integer playerId) {
        return playerRepository.findById(playerId);
    }

    public void deletePlayer(Integer playerId) {
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new AppException(
                "No player found with playerId: " + playerId));

        playerRepository.delete(player);
    }
}
