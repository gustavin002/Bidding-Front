/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.front.bidding.front.controller;

import com.bidding.front.bidding.front.model.UserRequestDTO;
import com.bidding.front.bidding.front.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String login(Model model) {
        
        UserRequestDTO credenciais = new UserRequestDTO();
        model.addAttribute("credenciais", credenciais);
        return "login";
    }

    @PostMapping("/logar")
    public String logar(@ModelAttribute UserRequestDTO credenciais, HttpSession session){
        session.setAttribute("email", credenciais.getEmail());
        
        return "redirect:/";
    }
    
}