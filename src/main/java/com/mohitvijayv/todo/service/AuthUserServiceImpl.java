package com.mohitvijayv.todo.service;

import com.mohitvijayv.todo.exception.UserNotFoundException;
import com.mohitvijayv.todo.exception.UsernameNotAvailableException;
import com.mohitvijayv.todo.model.AuthRole;
import com.mohitvijayv.todo.model.AuthUser;
import com.mohitvijayv.todo.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class AuthUserServiceImpl implements AuthUserService, UserDetailsService {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private AuthRoleService roleService;

    @Autowired
    private AuthUserRepository userRepository;

    @Override
    public AuthUser save(AuthUser user) {
        AuthUser newUser = this.findByUsername(user.getUsername());
        if(newUser != null)
            throw new UsernameNotAvailableException();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        AuthRole role = roleService.findByName("USER");
        Set<AuthRole> roleSet = new HashSet<>();
        roleSet.add(role);
        user.setRoles(roleSet);
        return userRepository.save(user);
    }

    @Override
    public AuthUser findById(Optional<Long> userId) {
        AuthUser user = userRepository.findById(userId);
        if(user==null){
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public AuthUser findByUsername(String username) {
        return userRepository.findByUsername(username); //implement null check
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("user 404");
        }
        return new AuthUserPrincipal(user);
    }
}
