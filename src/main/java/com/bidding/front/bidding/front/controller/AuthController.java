/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.front.bidding.front.controller;

import com.bidding.front.bidding.front.model.UserDTO;
import com.bidding.front.bidding.front.model.UserRequestDTO;
import com.bidding.front.bidding.front.service.AuthRestClientService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tools.jackson.databind.ObjectMapper;

/**
 * Controller responsável por atender às requisições de autenticação e páginas públicas.
 */
@Controller
public class AuthController {
    
    // Injeção do serviço de autenticação para delegar a lógica de login.    
    @Autowired
    private AuthRestClientService restService;
    
    // Tratador para requisições GET no caminho raiz "/".
    // Retorna o nome da view Thymeleaf "index".
    @GetMapping("/")
    public String home(
            HttpSession session
    ) {
        
        Object token = session.getAttribute("token");
        
        if(token == null) {
            return "redirect:/login";
        }
        
        return "index";
    }
    
    // Tratador para requisições GET em "/login".
    // Prepara o modelo com um objeto UserRequestDTO vazio para preencher o formulário.
    @GetMapping("/login")
    public String login(
        Model model
    ) {
        UserRequestDTO credenciais = new UserRequestDTO();
        model.addAttribute("credenciais", credenciais);
        return "login";
    }
    
    // Tratador para requisições POST em "/logar".
    // Recebe as credenciais submetidas pelo formulário e tenta autenticar.
    @PostMapping("/logar")
    public String logar(
            @ModelAttribute UserRequestDTO credenciais,
            HttpSession session
    ) {
        // Chama o serviço de autenticação para obter um token JWT ou similar.
        String token = restService.logar(credenciais);
        // Armazena o token na sessão HTTP para uso posterior.
        System.out.println("token: "+token);
        session.setAttribute("token", token);
        // Redireciona de volta para a página inicial após login bem sucedido.
        return "redirect:/";
    }
    
    @GetMapping("/registrar")
    public String registrar(
            Model model
    ) {
        UserDTO newUser = new UserDTO();
        model.addAttribute("user", newUser);
        return "registrar";
    }
    
    @PostMapping("/registrar")
    public String mandarRegistro(
            @ModelAttribute UserDTO user,
            RedirectAttributes redirectAttributes
    ) {

       try {
            restService.registrar(user);
            
            // Se o registro funcionar, envia uma mensagem de sucesso para a tela de login
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Cadastro realizado com sucesso! Faça o login.");
            return "redirect:/login";
            
        } catch (HttpStatusCodeException ex) {
            // Captura erros do backend (Ex: 400 - "Email já cadastrado", "Senha fraca", etc.)
            // ex.getStatusText() ou ex.getResponseBodyAsString() trazem o erro do backend
            String mensagemErroDoBackend = new ObjectMapper()
                    .readTree(
                            ex.getResponseBodyAsString()
                    ).get("message").asString(); 
            redirectAttributes.addFlashAttribute(
                    "erroServidor", 
                    mensagemErroDoBackend
            );
           
            return "redirect:/registrar"; // Redireciona de volta para o formulário mantendo o aviso
            
        } catch (Exception e) {
            
            redirectAttributes.addFlashAttribute("erroServidor", e.getMessage());
            return "redirect:/registrar";
        }
    }
}