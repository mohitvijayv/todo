package com.mohitvijayv.todo.service;

import com.mohitvijayv.todo.model.AuthRole;

public interface AuthRoleService {
    AuthRole findByName(String name);
}
