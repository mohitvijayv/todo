package com.mohitvijayv.todo.service;

import com.mohitvijayv.todo.model.AuthRole;
import com.mohitvijayv.todo.repository.AuthRoleRepository;
import com.mohitvijayv.todo.service.AuthRoleServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

class AuthRoleServiceImplTest {

    @Mock
    private AuthRoleRepository roleRepository;

    @InjectMocks
    private AuthRoleServiceImpl roleService;

    @Test
    void testFindByName(){
        MockitoAnnotations.initMocks(this);
        doReturn(new AuthRole()).when(roleRepository).findByRole(Matchers.anyString());

        roleService.findByName("USER");
        verify(roleRepository).findByRole("USER");
    }

}