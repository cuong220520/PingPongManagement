package com.PingPongManagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerAchievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playerAchievementId;

    @NotNull(message = "Ranking is required!")
    private String ranking;

    @NotNull(message = "Point is required!")
    private Double point;

    @NotNull(message = "Updated Date is required!")
    private Date updatedDate;

    @NotNull(message = "Nick Name is required!")
    private String nickName;

    @NotNull(message = "Player Code is required!")
    private String playerCode;

    @ManyToOne
    @JoinColumn(name = "playerId", referencedColumnName = "playerId")
    private Player player;
}
