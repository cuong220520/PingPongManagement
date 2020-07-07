package com.PingPongManagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resultId;

    @NotNull(message = "Set result is required!")
    private String set_1;

    @NotNull(message = "Set result is required!")
    private String set_2;

    @NotNull(message = "Set result is required!")
    private String set_3;

    @NotNull(message = "Set result is required!")
    private String set_4;

    @NotNull(message = "Set result is required!")
    private String set_5;
}
