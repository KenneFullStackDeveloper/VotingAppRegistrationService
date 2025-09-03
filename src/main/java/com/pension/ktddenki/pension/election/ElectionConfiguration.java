package com.pension.ktddenki.pension.election;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ElectionConfiguration {
    @Bean
    public WebClient webClient(@Value("${election-microservice.elections.url}") String baseUrl){
        return  WebClient.builder().baseUrl(baseUrl).build();
    }

}
