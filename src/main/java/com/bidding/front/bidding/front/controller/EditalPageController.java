/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.front.bidding.front.controller;

import com.bidding.front.bidding.front.model.EditalDTO;
import com.bidding.front.bidding.front.service.ApiService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditalPageController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/editais")
    public String listarEditais(Model model, HttpSession session) {

        if (session.getAttribute("TOKEN") == null) {
            return "redirect:/login";
        }

        String token = (String) session.getAttribute("TOKEN");

        List<EditalDTO> editais = apiService.listarEditais(token);

        model.addAttribute("editais", editais);

        return "editais";
    }
}