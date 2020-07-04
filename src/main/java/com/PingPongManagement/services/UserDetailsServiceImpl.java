package com.PingPongManagement.services;

import com.PingPongManagement.models.AppUser;
import com.PingPongManagement.repositories.AppRoleRepository;
import com.PingPongManagement.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    AppRoleRepository appRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> optionalUser = appUserRepository.findUserByUsername(username);

        AppUser user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("No user found " +
                "with username: " + username));

        List<String> roleNames = appRoleRepository.getRoleNames(user.getUserId());

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        if (roleNames != null) {
            for (String roleName : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(roleName);
                grantedAuthorityList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new User(user.getUsername(), user.getPassword(),
                grantedAuthorityList);

        return new User(user.getUsername(), user.getPassword(),
                grantedAuthorityList);
    }
}
