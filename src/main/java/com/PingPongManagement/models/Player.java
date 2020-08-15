package com.PingPongManagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotBlank(message = "Player Code is required!")
    private String playerCode;

    @NotBlank(message = "First Name is required!")
    private String firstName;

    @NotBlank(message = "Last Name is required!")
    private String lastName;

    private String nickName;

    @NotNull(message = "Date Of Birth is required!")
    private Date dateOfBirth;

    @NotBlank(message = "Ranking is required!")
    private String ranking;

    private Double updatedPoint;

    private Double accumulatedPoint;

    private String image;
}
