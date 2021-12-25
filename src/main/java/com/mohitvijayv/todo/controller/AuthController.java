package com.mohitvijayv.todo.controller;

import com.mohitvijayv.todo.exception.UsernameNotAvailableException;
import com.mohitvijayv.todo.model.AuthLoginUser;
import com.mohitvijayv.todo.model.AuthToken;
import com.mohitvijayv.todo.model.AuthUser;
import com.mohitvijayv.todo.security.TokenProvider;
import com.mohitvijayv.todo.service.AuthUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${jwt.token.validity}")
    public long TOKEN_VALIDITY;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthUserService userService;

    @Autowired
    TokenProvider tokenProvider;


    @PostMapping(path="/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add a new user", response = AuthUser.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created new User"),
            @ApiResponse(code = 400, message = "Invalid post body or parameter")
    })
    public ResponseEntity register(@ApiParam(value = "Information of new User") @RequestBody AuthUser user){
        userService.save(user);
        return new ResponseEntity(user, HttpStatus.CREATED);
    }

    @PostMapping(value = "/authenticate")
    @ApiOperation(value = "Authenticate a user", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully authenticated User"),
            @ApiResponse(code = 400, message = "Invalid post body or parameter")
    })
    public ResponseEntity<?> generateToken(@ApiParam(value = "User Credentials") @RequestBody AuthLoginUser loginUser) throws AuthenticationException {

        try{
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUser.getUsername(),
                            loginUser.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = tokenProvider.generateToken(authentication);

            return ResponseEntity.ok(new AuthToken(token, TOKEN_VALIDITY));
        } catch (Exception e){
            throw new UsernameNotAvailableException();
        }

    }







}
