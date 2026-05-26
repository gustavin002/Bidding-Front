/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.front.bidding.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/editais")
public class EditalPageController {
    
    
    @GetMapping()
    public String listarEditais (){
        
        return null;
    }
    
    @GetMapping()
    public String listarEditaisUrgenteTrue (){
        
        return null;
    }
}
