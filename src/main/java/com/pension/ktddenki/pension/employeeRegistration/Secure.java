package com.pension.ktddenki.pension.employeeRegistration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class Secure {
        @Autowired
        private CustomOauth2SuccessHandler successHandler;
        //private final UserService userService;
        private final RegistrationAuthService registrationAuthService;
        @Autowired
        private JwtAuthFilter jwtAuthFilter;
        public Secure(RegistrationAuthService registrationAuthService) {
            this.registrationAuthService = registrationAuthService;
            //this.userService = uds;
            //this.jwtAuthFilter = jwtAuthFilter;
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http    .cors(Customizer.withDefaults())
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/api/auth/logintest/**").permitAll()
                            .requestMatchers("/registration/**").permitAll()
                            .requestMatchers("/", "/login**", "/oauth2/**").permitAll()
                            .requestMatchers("/api/oauth/**").permitAll()
                            .requestMatchers("/registration/**").permitAll()
                            .requestMatchers("/api/auth/details/**").authenticated()
                            .requestMatchers("/api/auth/user/**").hasAuthority("ROLE_USER")
                            .anyRequest().authenticated()
                    )
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                    .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .httpBasic(Customizer.withDefaults())
                    .oauth2Login(oauth-> oauth
                            .userInfoEndpoint(userInfo -> userInfo
                                    .userService(userRequest -> {
                                        var delegate = new DefaultOAuth2UserService();
                                        var oauth2User = delegate.loadUser(userRequest);

                                        // Assign ROLE_APPLICANT to every OAuth user
                                        return new DefaultOAuth2User(
                                                List.of(new SimpleGrantedAuthority("ROLE_APPLICANT")),
                                                oauth2User.getAttributes(),
                                                "email"
                                        );
                                    })


                            )
                            //.successHandler(successHandler)

                    );

            return http.build();
        }

        @Bean
        public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder encoder) throws Exception {
            AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
            //builder.userDetailsService(userService).passwordEncoder(encoder);
            builder.userDetailsService(registrationAuthService).passwordEncoder(encoder);
            return builder.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        }

    @Bean
    UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http:localhost","http://localhost:5174","http://localhost:5173","http://localhost:5175", "http://192.168.178.194"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("Authorization"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    }



