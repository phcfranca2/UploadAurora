package com.aurora.webgames.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="usuarios")
public class UsuarioAuroraModel{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdUser;

    @NotEmpty(message = "Preencha o campo senha!")
    private String senhaUser;

    @NotEmpty(message = "Preencha o campo usuário!")
    private String nome;

    public Long getCdUser() {
        return cdUser;
    }

    public void setCdUser(Long cdUser) {
        this.cdUser = cdUser;
    }

    public String getSenhaUser() {
        return senhaUser;
    }

    public void setSenhaUser(String senhaUser) {
        this.senhaUser = senhaUser;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
