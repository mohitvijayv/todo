package com.mohitvijayv.todo.controller;

import com.mohitvijayv.todo.model.AuthUser;
import com.mohitvijayv.todo.service.AuthUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired
    private AuthUserService userService;

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="get user details", response = AuthUser.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched user details")
    })
    public ResponseEntity<?> getUser(Principal principal){
        return ResponseEntity.ok(userService.findByUsername(principal.getName()));
    }
}
