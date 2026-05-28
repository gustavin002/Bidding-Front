/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.front.bidding.front.model;

// Classe de transferência de dados (Data Transfer Object) para usuários
// DTO é usado para transferir dados entre camadas da aplicação (Controller -> Service -> Repository)
public class UserDTO {
    // Identificador único do usuário no banco de dados
    private Long id;
    // Nome completo do usuário
    private String nome;
    // Email do usuário (deve ser único)
    private String email;
    // Senha do usuário (armazenada criptografada)
    private String senha;
    // Papel/função do usuário (exemplo: "COMPRADOR" ou "FORNECEDOR")
    private String role;
    
    private String confirmarSenha;

    // Construtor vazio (padrão do JavaBeans)
    public UserDTO() {
    }

    // Construtor com todos os parâmetros
    // Permite criar um usuário completamente inicializado em uma única chamada
    public UserDTO(Long id, String nome, String email, String senha, String role, String confirmarSenha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
        this.confirmarSenha = confirmarSenha;
    }

    // Método getter - retorna o id do usuário
    public Long getId() {
        return id;
    }

    // Método setter - define o id do usuário
    public void setId(Long id) {
        this.id = id;
    }

    // Método getter - retorna o nome do usuário
    public String getNome() {
        return nome;
    }

    // Método setter - define o nome do usuário
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Método getter - retorna o email do usuário
    public String getEmail() {
        return email;
    }

    // Método setter - define o email do usuário
    public void setEmail(String email) {
        this.email = email;
    }

    // Método getter - retorna a senha do usuário
    public String getSenha() {
        return senha;
    }

    // Método setter - define a senha do usuário
    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Método getter - retorna o papel/função do usuário
    public String getRole() {
        return role;
    }

    // Método setter - define o papel/função do usuário
    public void setRole(String role) {
        this.role = role;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }
  
}