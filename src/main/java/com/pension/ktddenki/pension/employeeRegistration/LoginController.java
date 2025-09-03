package com.pension.ktddenki.pension.employeeRegistration;





import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")

public class LoginController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/logintest")
    public Map<String, String> login(@RequestBody UserDTO user) {
        System.out.println("...................................ääää"+user.getEmail());
        Map<String, String> response = new HashMap<>();
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );

            /**

            if (auth.isAuthenticated()){
                UsertestModel usertestModel = (UsertestModel) auth.getPrincipal();
                String token = jwtUtil.generateToken(usertestModel.getUsername());

                response.put("token", token);



            }

             **/

            if (auth.isAuthenticated()){
                // UsertestModel usertestModel = (UsertestModel) auth.getPrincipal();
                UserRegistrationModel userRegistrationModel = (UserRegistrationModel) auth.getPrincipal();
                String token = jwtUtil.generateToken(userRegistrationModel.getUsername());

                response.put("token", token);



            }

            return response;

        } catch (BadCredentialsException e) {
            response.put("token", e.getMessage());
            return response ;

        }
    }


    /**

    @GetMapping("/user")
    public List<UserRegistrationModel> login() {

        return loginRepository.findAll();

    }
     **/
    @PostMapping("/login")
    public Map<String, String> login(Principal principal) {
        String username = principal.getName(); // fetched from Spring Security

        // Generate token
        String token = jwtUtil.generateToken(username);

        // Return as JSON
        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return response;


    }



    @GetMapping("/details")
    public Map<String, Object> getUserDetails(Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Map<String, Object> response = new HashMap<>();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            response.put("username", userDetails.getUsername());
            response.put("roles", userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList()));
        } else {
            response.put("error", "User not authenticated");
        }

        return response;
    }


}
