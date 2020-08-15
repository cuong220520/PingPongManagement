package com.PingPongManagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer userId;

    @NotBlank(message = "Username must not be blank!")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Password must not be blank!")
    private String password;

    @OneToOne
    @JoinColumn(name = "teamId", referencedColumnName = "teamId")
    private Team team;
}
