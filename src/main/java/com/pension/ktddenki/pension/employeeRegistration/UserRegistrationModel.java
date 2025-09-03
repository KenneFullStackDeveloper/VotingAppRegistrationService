package com.pension.ktddenki.pension.employeeRegistration;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name="registration")
public class UserRegistrationModel implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false,name = "electionname")
    private String electionName;

    @Column(nullable = false)
    private String password;

    @Column (nullable = false)
    private String roles;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + roles.toUpperCase()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
    public String getName(){
        return this.name;
    }

    public String getElectionName(){
        return this.electionName;
    }

    public String getState(){
        return this.state;

    }

    public String getEmail(){
        return this.email;
    }



    public String getRoles() {
        return this.roles;
    }


    public void setPassword(String pass) {
        this.password = pass;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setName(String name) {
        this.name= name;
    }
    public void setElectionName(String electionName) {
        this.electionName= electionName;
    }
    public void setState(String state) {
        this.state= state;
    }


}
