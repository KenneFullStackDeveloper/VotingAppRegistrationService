package com.pension.ktddenki.pension.employeeRegistration;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


/**

@Entity
@Table(name="usertest")
public class UsertestModel implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    @Column(nullable = false)
    private String email;



    @Column(nullable = false)
    private String password;

    @Column
    private String roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + roles.toUpperCase()));
    }

    public String getPassword(){
        return this.password;
    }


    @Override
    public String getUsername() {
        return email;
    }

    public String getEmail() {
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



}

**/