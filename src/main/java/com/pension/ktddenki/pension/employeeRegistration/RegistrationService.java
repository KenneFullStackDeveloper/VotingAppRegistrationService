package com.pension.ktddenki.pension.employeeRegistration;

import com.pension.ktddenki.pension.election.Election;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RegistrationService  {
    @Autowired RegistrationRepository registrationRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    WebClient webClient;

    Map <String,String> userSaved;
    public Optional<UserRegistrationModel> isUserEmailExist(String email){
        return registrationRepository.findByEmail(email);

    }

    public Map<String,String> saveUser(RegistrationDTO registrationDTO) {
        userSaved = new HashMap<>();
        System.out.println("user saved rrr......"+registrationDTO.getRoles());
        UserRegistrationModel userRegistrationModel = new UserRegistrationModel();
        userRegistrationModel.setEmail(registrationDTO.getEmail());
        userRegistrationModel.setName(registrationDTO.getName());
        userRegistrationModel.setState(registrationDTO.getState());
        userRegistrationModel.setElectionName(registrationDTO.getElectionName());

        // encode password
        String encodedPassword = passwordEncoder.encode(registrationDTO.getPassword());
        userRegistrationModel.setPassword(encodedPassword);
        userRegistrationModel.setRoles(registrationDTO.getRoles());
        Optional<UserRegistrationModel> user = isUserEmailExist(registrationDTO.getEmail());

        try {
            Election election = findElection(userRegistrationModel.getElectionName());
            if (user.isEmpty() && election !=null){
                System.out.println("user saved rrr......"+ userRegistrationModel.getElectionName());
                System.out.println("user saved rrr......"+ userRegistrationModel.getRoles());
                registrationRepository.save(userRegistrationModel);
                userSaved.put("save", "success");
                return userSaved;
            }

            userSaved.put("save","fail");
            return userSaved;

        } catch (Exception e) {
            System.out.println("errorrrr"+ e.getMessage());
        }
        return null;

    }

    public Election findElection (String name){
        Election myElection = webClient.get()
                .uri("election/{name}",name)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(Election.class)
                .block();
        return myElection;

    }


}
