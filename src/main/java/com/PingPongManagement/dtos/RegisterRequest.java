package com.PingPongManagement.dtos;

import com.PingPongManagement.models.AppRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotNull(message = "Username is required!")
    @NotBlank(message = "Username can not be empty!")
    private String username;

    @NotNull(message = "Password can not be empty!")
    private String password;

    @NotNull(message = "Role is required!")
    private AppRole appRole;
}
