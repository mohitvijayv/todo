package com.mohitvijayv.todo.repository;

import com.mohitvijayv.todo.model.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRoleRepository extends JpaRepository<AuthRole, Long> {
    AuthRole findByRole(String name);
}
