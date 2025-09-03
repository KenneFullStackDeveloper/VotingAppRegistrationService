package com.pension.ktddenki.pension.employeeRegistration;

public class RegistrationDTO {

    private int id;
    private String email;
    private String name;
    private String electionName;
    private String state;
    private String password;
    private String roles;


    public String getName(){
        return this.name;
    }
    public String getElectionName(){
        return this.electionName;
    }

    public String getState(){
        return this.state;
    }

    public String getRoles(){
        return roles;
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
