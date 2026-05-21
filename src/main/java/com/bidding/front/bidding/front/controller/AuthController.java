/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.front.bidding.front.controller;

import com.bidding.front.bidding.front.model.LoginDTO;
import com.bidding.front.bidding.front.model.LoginResponseDTO;
import com.bidding.front.bidding.front.model.UserDTO;
import com.bidding.front.bidding.front.service.ApiService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/login")
    public String telaLogin(Model model) {

        model.addAttribute("loginDTO", new LoginDTO());

        return "login";
    }

    @PostMapping("/login")
    public String login(LoginDTO loginDTO, HttpSession session, Model model) {

        try {

            LoginResponseDTO response = apiService.logar(loginDTO);

            session.setAttribute("TOKEN", response.getToken());

            session.setAttribute("ROLE", response.getRole());

            session.setAttribute("EMAIL", response.getEmail());

            return "redirect:/editais";

        } catch (Exception e) {

            model.addAttribute("erro", "Email ou senha inválidos");

            return "login";
        }
    }

    @GetMapping("/register")
    public String telaRegister(Model model) {
        model.addAttribute("userDTO", new UserDTO());

        return "register";
    }

    @PostMapping("/register")
    public String registrar(UserDTO userDTO, Model model) {

        try {

            apiService.registrarUsuario(userDTO);

            return "redirect:/login";

        } catch (Exception e) {

            model.addAttribute("erro", "Erro ao registrar usuário");

            return "register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }
}