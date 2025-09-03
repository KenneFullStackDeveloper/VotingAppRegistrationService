package com.pension.ktddenki.pension.employeeRegistration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
@Service
public class UserService  implements UserDetailsService  {
      @Autowired
      private LoginRepository loginRepository;

    public UsertestModel usertestModelPass(String password){
        return loginRepository.findByPassword(password);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loginRepository.findByEmail(username) .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


}
**/