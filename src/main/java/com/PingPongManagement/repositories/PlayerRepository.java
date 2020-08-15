package com.PingPongManagement.repositories;

import com.PingPongManagement.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    /*List<Player> findByFirstNameContaining(String term);
    List<Player> findByLastNameContaining(String term);
    List<Player> findByPlayerCodeContaining(String term);*/

    List<Player> findByFirstNameContainingOrLastNameContainingOrPlayerCodeContaining(String termFirstName,
                                                                   String termLastName,
                                                                   String termPlayerCode);
}
