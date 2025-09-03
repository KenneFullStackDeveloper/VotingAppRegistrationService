package com.pension.ktddenki.pension.employeeRegistration;

import com.pension.ktddenki.pension.election.Election;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RegistrationAuthService implements UserDetailsService {

    @Autowired
    RegistrationRepository registrationRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         return registrationRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
