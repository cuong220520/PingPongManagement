package com.PingPongManagement.dtos;

import com.PingPongManagement.models.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String username;
    private Collection<GrantedAuthority> grantedAuthorityList;
    private Team team;
}
