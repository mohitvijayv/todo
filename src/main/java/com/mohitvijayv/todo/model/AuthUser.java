package com.mohitvijayv.todo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "auth_user")
@ApiModel
public class AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_user_id")
    @ApiModelProperty(notes = "User's ID", position = 1)
    private Long id;
    @Column(name = "first_name")
    @ApiModelProperty(notes = "User's first name", position = 2)
    private String firstName;

    public AuthUser(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    @Column(name = "last_name")
    @ApiModelProperty(notes = "User's last name", position = 3)
    private String lastName;
    @Column(name = "username")
    @ApiModelProperty(notes = "User's username", position = 4)
    private String username;
    @Column(name = "password")
    @ApiModelProperty(notes = "User's password", position = 5)
    private String password;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "auth_user_role", joinColumns = @JoinColumn(name = "auth_user_id"), inverseJoinColumns = @JoinColumn(name = "auth_role_id"))

    private Set<AuthRole> roles = new HashSet<AuthRole>();

    public AuthUser() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Set<AuthRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AuthRole> roles) {
        this.roles = roles;
    }
}
