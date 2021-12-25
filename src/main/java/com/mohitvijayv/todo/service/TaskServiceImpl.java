package com.mohitvijayv.todo.service;

import com.mohitvijayv.todo.exception.OperationNotAllowedException;
import com.mohitvijayv.todo.exception.TaskNotFoundException;
import com.mohitvijayv.todo.exception.UsernameNotAvailableException;
import com.mohitvijayv.todo.model.Task;
import com.mohitvijayv.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.*;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AuthMyUserDetailsService userDetailsService;

    @Autowired
    private AuthUserService userService;

    public Task create(Task task, AuthUserPrincipal principal){
        Date currentTime = new Date();
        Timestamp now = new java.sql.Timestamp(currentTime.getTime());
        String creator = principal.getUsername();
        Set<String> roles = userDetailsService.getRoleSet(principal);
        Optional<Long> userId = Optional.of(task.getUserId());
        String username = userService.findById(userId).getUsername();
        if(!username.equals(principal.getUsername()) && !roles.contains("ADMIN"))
        {
            throw new OperationNotAllowedException();
        }
        task.setUsername(username);
        task.setCreator(creator);
        task.setLastModified(now);
        return taskRepository.save(task);
    }

    public Page<Task> findAll(AuthUserPrincipal principal, Pageable pageable){
        Set<String> roles = userDetailsService.getRoleSet(principal);
        if(roles.contains("ADMIN"))
            return taskRepository.findAll(pageable);
        else
            throw new OperationNotAllowedException();
    }

    public Page findByUserId(Optional<Long> userId, AuthUserPrincipal principal, Pageable pageable){
        Set<String> roles = userDetailsService.getRoleSet(principal);
        if(principal.getUsername().equals(userService.findById(userId).getUsername()) || roles.contains("ADMIN")) {
            return taskRepository.findByUserId(userId, pageable);
        }
        throw new UsernameNotAvailableException();
    }

    public Task findById(Long taskId){
        return taskRepository.findById(taskId).orElseThrow(()->new TaskNotFoundException());
    }

    public void delete(Long taskId){
        if(taskRepository.existsById(taskId)){
            Task task = taskRepository.getOne(taskId);
            taskRepository.delete(task);
        }
        else {
            throw new TaskNotFoundException();
        }
    }
}
