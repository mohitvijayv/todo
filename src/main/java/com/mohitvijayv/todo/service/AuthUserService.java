package com.mohitvijayv.todo.service;

import com.mohitvijayv.todo.model.AuthUser;

import java.util.Optional;

public interface AuthUserService {

    AuthUser save(AuthUser user);

    AuthUser findById(Optional<Long> userId);

    AuthUser findByUsername(String username);

}
