/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.front.bidding.front.service;

import com.bidding.front.bidding.front.model.EditalDTO;
import com.bidding.front.bidding.front.model.LoginDTO;
import com.bidding.front.bidding.front.model.LoginResponseDTO;
import com.bidding.front.bidding.front.model.UserDTO;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    private final String API_URL = "http://localhost:9000";

    private final RestTemplate restTemplate =
            new RestTemplate();

    public LoginResponseDTO logar(LoginDTO loginDTO) {

        HttpEntity<LoginDTO> request =
                new HttpEntity<>(loginDTO);

        ResponseEntity<LoginResponseDTO> response =
                restTemplate.postForEntity(
                        API_URL + "/api/auth/logar",
                        request,
                        LoginResponseDTO.class
                );

        return response.getBody();
    }
    
    public void registrarUsuario(UserDTO userDTO) {

        HttpEntity<UserDTO> request =
                new HttpEntity<>(userDTO);

        restTemplate.postForEntity(
                API_URL + "/api/auth/registrar",
                request,
                String.class
        );
    }

    public List<EditalDTO> listarEditais(String token) {

        HttpHeaders headers = new HttpHeaders();

        headers.setBearerAuth(token);

        HttpEntity<Void> entity =
                new HttpEntity<>(headers);

        ResponseEntity<EditalDTO[]> response = restTemplate.exchange(API_URL + "/api/editais", HttpMethod.GET, entity, EditalDTO[].class);

        return Arrays.asList(response.getBody());
    }
}