package com.PingPongManagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playerId;

    @Column(unique = true)
    @NotNull(message = "Player Code is required!")
    private String playerCode;

    @NotNull(message = "First Name is required!")
    private String firstName;

    @NotNull(message = "Last Name is required!")
    private String lastName;

    private String nickName;

    @NotNull(message = "Date Of Birth is required!")
    private Date dateOfBirth;

    @NotNull(message = "Ranking is required!")
    private String ranking;

    private Double updatedPoint;

    private Double accumulatedPoint;

    private String image;
}
