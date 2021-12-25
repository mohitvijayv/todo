package com.mohitvijayv.todo.service;

import com.mohitvijayv.todo.model.AuthUser;
import com.mohitvijayv.todo.model.Task;
import com.mohitvijayv.todo.repository.TaskRepository;
import com.mohitvijayv.todo.service.AuthMyUserDetailsService;
import com.mohitvijayv.todo.service.AuthUserPrincipal;
import com.mohitvijayv.todo.service.AuthUserService;
import com.mohitvijayv.todo.service.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
class TaskServiceImplTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private AuthUserService userService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private AuthMyUserDetailsService myUserService;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        Set<String> roleSet = new HashSet<>();
        roleSet.add("ADMIN");
        when(myUserService.getRoleSet(any())).thenReturn(roleSet);
        when(userService.findById(any()).getUsername()).thenReturn("testuser");
    }

    @Test
    void testCreate(){
        AuthUser user = new AuthUser("test", "user", "testuser", "password");
        AuthUserPrincipal principal = new AuthUserPrincipal(user);
        Task task = new Task(99l, "task-1", "new task", Timestamp.valueOf("1970-01-01 08:55:45.666"), true);

        taskService.create(task, principal);

        verify(taskRepository).save(task);
        assertEquals("testuser", task.getCreator());
        assertNotNull(task.getUsername());
        assertNotNull(task.getLastModified());
    }

    @Test
    void testFindAll_whenRoleAdmin_thenReturnList(){
        when(taskRepository.findAll((Pageable) any())).thenReturn(Page.empty());
        AuthUser user = new AuthUser("test", "user", "testuser", "password");
        AuthUserPrincipal principal = new AuthUserPrincipal(user);

        taskService.findAll(principal, Pageable.unpaged());

        verify(taskRepository).findAll((Pageable) any());
    }

    @Test
    void testFindByUserId(){
        when(taskRepository.findByUserId(any(), any())).thenReturn(Page.empty());
        AuthUser user = new AuthUser("test", "user", "testuser", "password");
        AuthUserPrincipal principal = new AuthUserPrincipal(user);

        taskService.findByUserId(java.util.Optional.of(1l), principal, Pageable.unpaged());

        verify(taskRepository).findByUserId(any(), any());
    }

    @Test
    void testFindById(){
        when(taskRepository.findById(any())).thenReturn(Optional.of(new Task()));

        taskService.findById(1l);

        verify(taskRepository).findById(any());
    }

    @Test
    void testDelete(){
        when(taskRepository.existsById(1l)).thenReturn(true);
        when(taskRepository.getOne(1l)).thenReturn(new Task());
        taskService.delete(1l);

        verify(taskRepository).delete(any());
    }

}