package com.PingPongManagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenRequest {
    @NotNull(message = "Refresh Token is required!")
    @NotBlank(message = "Refresh Token can not be empty!")
    private String refreshToken;
    @NotNull(message = "Username is required!")
    @NotBlank(message = "Username can not be empty!")
    private String username;
}
