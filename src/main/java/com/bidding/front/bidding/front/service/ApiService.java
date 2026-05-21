/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.front.bidding.front.service;

import com.bidding.front.bidding.front.model.EditalDTO;
import com.bidding.front.bidding.front.model.LanceDTO;
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

   private static final String API_URL = "http://localhost:9000";
 
    private final RestTemplate restTemplate;
 
    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
 
    public LoginResponseDTO logar(LoginDTO loginDTO) {
        HttpEntity<LoginDTO> request = new HttpEntity<>(loginDTO);
        ResponseEntity<LoginResponseDTO> response = restTemplate.postForEntity(
                API_URL + "/api/auth/logar", request, LoginResponseDTO.class);
        return response.getBody();
    }
 
    public void registrarUsuario(UserDTO userDTO) {
        HttpEntity<UserDTO> request = new HttpEntity<>(userDTO);
        restTemplate.postForEntity(
                API_URL + "/api/auth/registrar", request, String.class);
    }
 
    public List<EditalDTO> listarEditais(String token) {
        HttpEntity<Void> entity = new HttpEntity<>(bearerHeaders(token));
        ResponseEntity<EditalDTO[]> response = restTemplate.exchange(
                API_URL + "/api/editais", HttpMethod.GET, entity, EditalDTO[].class);
        return Arrays.asList(response.getBody());
    }
 
    public EditalDTO buscarEdital(Long id, String token) {
        HttpEntity<Void> entity = new HttpEntity<>(bearerHeaders(token));
        ResponseEntity<EditalDTO> response = restTemplate.exchange(
                API_URL + "/api/editais/" + id, HttpMethod.GET, entity, EditalDTO.class);
        return response.getBody();
    }
 
    public void criarEdital(EditalDTO editalDTO, String token) {
        HttpHeaders headers = bearerHeaders(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<EditalDTO> request = new HttpEntity<>(editalDTO, headers);
        restTemplate.postForEntity(
                API_URL + "/api/editais", request, String.class);
    }
 
    public void registrarLance(Long editalId, LanceDTO lanceDTO, String token) {
        HttpHeaders headers = bearerHeaders(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LanceDTO> request = new HttpEntity<>(lanceDTO, headers);
        restTemplate.postForEntity(
                API_URL + "/api/editais/" + editalId + "/lances", request, String.class);
    }
 
    public List<LanceDTO> listarLancesPorEdital(Long editalId, String token) {
        HttpEntity<Void> entity = new HttpEntity<>(bearerHeaders(token));
        ResponseEntity<LanceDTO[]> response = restTemplate.exchange(
                API_URL + "/api/editais/" + editalId + "/lances",
                HttpMethod.GET, entity, LanceDTO[].class);
        return Arrays.asList(response.getBody());
    }
 
    public List<LanceDTO> listarMeusLances(String token) {
        HttpEntity<Void> entity = new HttpEntity<>(bearerHeaders(token));
        ResponseEntity<LanceDTO[]> response = restTemplate.exchange(
                API_URL + "/api/lances/meus-lances",
                HttpMethod.GET, entity, LanceDTO[].class);
        return Arrays.asList(response.getBody());
    }
 
    private HttpHeaders bearerHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        return headers;
    }
}