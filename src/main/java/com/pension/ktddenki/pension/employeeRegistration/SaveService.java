package com.pension.ktddenki.pension.employeeRegistration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**

@Service
public class SaveService {
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsertestModel loadData (RegistryDTO registryDTO){
        UsertestModel usertestModel = new UsertestModel();
        usertestModel.setEmail(registryDTO.getEmail());
        usertestModel.setId(registryDTO.getIdd());
        usertestModel.setRoles(registryDTO.getRole());

        String encodedPassword = passwordEncoder.encode(registryDTO.getPassword());
        usertestModel.setPassword(encodedPassword);

       return loginRepository.save(usertestModel);

    }






}
**/