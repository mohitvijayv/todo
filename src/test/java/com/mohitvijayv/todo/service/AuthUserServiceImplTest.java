package com.mohitvijayv.todo.service;

import com.mohitvijayv.todo.model.AuthRole;
import com.mohitvijayv.todo.model.AuthUser;
import com.mohitvijayv.todo.repository.AuthUserRepository;
import com.mohitvijayv.todo.service.AuthRoleServiceImpl;
import com.mohitvijayv.todo.service.AuthUserPrincipal;
import com.mohitvijayv.todo.service.AuthUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
class AuthUserServiceImplTest {

    @Mock
    private AuthRoleServiceImpl roleService;

    @Mock
    private AuthUserRepository userRepository;

    @InjectMocks
    private AuthUserServiceImpl userService;

    private AuthUser user;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        user = new AuthUser("test", "user", "testuser", "password");
        AuthUserPrincipal principal = new AuthUserPrincipal(user);
    }

    @Test
    void testSave(){
        when(roleService.findByName(Matchers.anyString())).thenReturn(new AuthRole("USER", "user-level"));
        userService.save(user);
    }

    @Test
    void testFindById(){
        doReturn(user).when(userRepository).findById(java.util.Optional.of(1L));
        userService.findById(java.util.Optional.of(1L));

        verify(userRepository).findById(java.util.Optional.of(1L));
    }

    @Test
    void testFindByUsername(){
        userService.findByUsername("testuser");

        verify(userRepository).findByUsername("testuser");
    }



}