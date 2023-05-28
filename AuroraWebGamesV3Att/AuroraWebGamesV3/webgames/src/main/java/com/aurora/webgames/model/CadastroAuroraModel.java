package com.aurora.webgames.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="categoriaJogos")
public class CadastroAuroraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdCat;

    @NotEmpty(message = "O tipo da categoria deve ser informada!")
    private String teste;

    public Long getCdCat() {
        return cdCat;
    }

    public void setCdCat(Long cdCat) {
        this.cdCat = cdCat;
    }
	
    public String getTeste(){
        return teste;
    }

    public void setTeste(String teste) {
        this.teste = teste;
    }
}

