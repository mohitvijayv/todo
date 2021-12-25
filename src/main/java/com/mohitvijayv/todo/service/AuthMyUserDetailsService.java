package com.mohitvijayv.todo.service;
import com.mohitvijayv.todo.model.AuthRole;
import com.mohitvijayv.todo.model.AuthUser;
import com.mohitvijayv.todo.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;


@Service
public class AuthMyUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("user 404");
        }
        return new AuthUserPrincipal(user);
    }

    public Set<String> getRoleSet(AuthUserPrincipal principal){
        AuthUser user = userRepository.findByUsername(principal.getUsername());
        return user.getRoles().stream().map(AuthRole::getRole).collect(Collectors.toSet());
    }

}
