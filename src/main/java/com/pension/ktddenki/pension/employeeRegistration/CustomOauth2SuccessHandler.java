package com.pension.ktddenki.pension.employeeRegistration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomOauth2SuccessHandler  implements AuthenticationSuccessHandler {




        @Override
        public void onAuthenticationSuccess(HttpServletRequest request,
                                            HttpServletResponse response,
                                            Authentication authentication) throws IOException {


            String redirectUrl = "http://localhost:5173";

            // Exemple avec un token dans l'URL :
            // String redirectUrl = "http://localhost:3000/accueil?token=" + token;

            response.sendRedirect(redirectUrl);
        }
    }



