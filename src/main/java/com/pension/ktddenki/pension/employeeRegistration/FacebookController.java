package com.pension.ktddenki.pension.employeeRegistration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/oauth")
public class FacebookController {

    @Value("${spring.security.oauth2.client.registration.facebook.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.facebook.client-secret}")
    private String clientSecret;

    @GetMapping("/user")
    public Map<String, Object> getUserInfo(OAuth2AuthenticationToken authentication) {
        if (authentication == null) {
            throw new IllegalStateException("User is not authenticated");
        }

        OidcUser user = (OidcUser) authentication.getPrincipal();
        return user.getAttributes();
    }

    @GetMapping("/token")
    public String getAccessToken(OAuth2AuthenticationToken authentication) {
        OidcUser user = (OidcUser) authentication.getPrincipal();
        return user.getIdToken().getTokenValue();
    }

    @PostMapping("/exchange-token")
    public Map<String, String> exchangeToken(@RequestBody Map<String, String> body) {

        System.out.println("tetttttttt++++++++++++++++++++++++++++++++++++");
        String code = body.get("code");

        String redirectUri = "http://localhost:5175/oauthlogon";

        String tokenUrl = "https://graph.facebook.com/oauth/access_token";

        Map<String, String> params = new HashMap<>();
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("code", code);
        params.put("redirect_uri", redirectUri);


        RestTemplate restTemplate = new RestTemplate();
        ParameterizedTypeReference<Map<String, Object>> typeRef =
                new ParameterizedTypeReference<Map<String, Object>>() {};

        ResponseEntity<Map<String, Object>> response
                = restTemplate.exchange(tokenUrl, HttpMethod.POST, new HttpEntity<>(params), typeRef);

        String accessToken = (String) response.getBody().get("access_token");

        Map<String, String> result = new HashMap<>();
        result.put("token", accessToken);
        return result;
    }

    @GetMapping("/user-details")
    public Map<String, Object> getUserInfo(@RequestHeader("Authorization") String token) {
        String accessToken = token.replace("Bearer ", "");
        //Verify the id token with facebook
        String userInfoEndpoint = "https://graph.facebook.com/me?fields=id,name,email&access_token=" + accessToken;
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                userInfoEndpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String, Object>>() {}
        );

        Map<String, Object> body = response.getBody();
        return body;
    }




}
