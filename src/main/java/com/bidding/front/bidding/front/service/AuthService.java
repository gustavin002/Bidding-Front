/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.front.bidding.front.service;

import com.bidding.front.bidding.front.model.UserRequestDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {
    
    private RestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8883/api";
    
    public String logar(UserRequestDTO user){
        HttpEntity<UserRequestDTO> body = new HttpEntity<>(user);
        
        return restTemplate.exchange(BASE_URL + "/auth/logar", HttpMethod.POST, body, String.class).getBody();
    }
    
}
