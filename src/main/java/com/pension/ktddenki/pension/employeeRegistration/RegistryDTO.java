package com.pension.ktddenki.pension.employeeRegistration;

public class RegistryDTO {
    private int id;
    private String email;
    private String password;
    private String role;



    public String getRole(){
        return role;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }

    public int getIdd(){
        return id;
    }
}
