package com.pension.ktddenki.pension.employeeRegistration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
/**
@RestController

public class RegistryController {


    private final SaveService saveService;

    public RegistryController(SaveService saveService) {
        this.saveService = saveService;
    }

    @PostMapping("/registrations")
    public UsertestModel registry(@RequestBody RegistryDTO registryDTO) {
         System.out.println("yes-------------------------------------------------------"+ registryDTO.getEmail());
         UsertestModel user = saveService.loadData(registryDTO);

         return user;


    }


}
**/