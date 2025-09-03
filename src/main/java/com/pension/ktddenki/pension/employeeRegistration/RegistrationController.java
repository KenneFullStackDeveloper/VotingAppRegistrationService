package com.pension.ktddenki.pension.employeeRegistration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RegistrationController {
    private final RegistrationService registrationService;


    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/registration")
    public Map<String, String> registration(@RequestBody RegistrationDTO registrationDTO) {
        System.out.println("==== DEBUG DTO ====");
        System.out.println("Name: " + registrationDTO.getName());
        System.out.println("Email: " + registrationDTO.getEmail());
        System.out.println("ElectionName: " + registrationDTO.getElectionName());  // 👈 important
        System.out.println("State: " + registrationDTO.getState());
        System.out.println("Password: " + registrationDTO.getPassword());
        System.out.println("Role: " + registrationDTO.getRoles());

        Map<String, String> response = registrationService.saveUser(registrationDTO);
        if ("success".equals(response.get("save"))) {
            return ResponseEntity.ok(response).getBody(); // 200 OK avec JSON
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response).getBody(); // 400 Bad Request
        }


    }




}
