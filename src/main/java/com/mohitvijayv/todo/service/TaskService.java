package com.mohitvijayv.todo.service;

import com.mohitvijayv.todo.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.Optional;

public interface TaskService {
    Task create(Task task, AuthUserPrincipal principal);

    Page<Task> findAll(AuthUserPrincipal principal, Pageable pageable);

    Page findByUserId(Optional<Long> userId, AuthUserPrincipal principal, Pageable pageable);

    Task findById(Long taskId);

    void delete(Long taskId);



}
