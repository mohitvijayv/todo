package com.mohitvijayv.todo.service;

import com.mohitvijayv.todo.model.AuthRole;
import com.mohitvijayv.todo.repository.AuthRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthRoleServiceImpl implements AuthRoleService {

    @Autowired
    private AuthRoleRepository roleRepository;

    @Override
    public AuthRole findByName(String name) {
        AuthRole role  = roleRepository.findByRole(name);
        return role;
    }
}
