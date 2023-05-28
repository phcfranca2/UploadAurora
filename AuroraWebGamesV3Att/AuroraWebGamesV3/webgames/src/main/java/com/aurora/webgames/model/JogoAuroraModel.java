package com.aurora.webgames.model;

import java.util.Date; 
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="jogosAurora")
public class JogoAuroraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdJogo;

    @NotEmpty(message = "O nome do jogo deve ser informado!")
    private String nmJogo;

    private String dtLancJogo;

    @Lob
    private byte[] imgJogo;

    @NotEmpty(message = "Informe um video apresentativo jogo!")
    private String vdApresentaJogo;

    @NotEmpty(message = "Informe a rota de acesso ao jogo!")
    private String linkJogo;

    private String catJogo;

    public Long getCdJogo() {
        return cdJogo;
    }

    public void setCdJogo(Long cdJogo) {
        this.cdJogo = cdJogo;
    }

    public String getNmJogo() {
        return nmJogo;
    }

    public void setNmJogo(String nmJogo) {
        this.nmJogo = nmJogo;
    }

    public String getDtLancJogo() {
        return dtLancJogo;
    }

    public void setDtLancJogo(String dtLancJogo) {
        this.dtLancJogo = dtLancJogo;
    }

    public byte[] getImgJogo() {
        return imgJogo;
    }

    public void setImgJogo(byte[] imgJogo) {
        this.imgJogo = imgJogo;
    }

    public String getVdApresentaJogo() {
        return vdApresentaJogo;
    }

    public void setVdApresentaJogo(String vdApresentaJogo) {
        this.vdApresentaJogo = vdApresentaJogo;
    }

    public String getLinkJogo() {
        return linkJogo;
    }

    public void setLinkJogo(String linkJogo) {
        this.linkJogo = linkJogo;
    }    

    public String getCatJogo(){
        return catJogo;
    }

    public void setCatJogo(String catJogo) {
        this.catJogo = catJogo;
    }

    @Override
	public int hashCode() {
		return Objects.hash(cdJogo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JogoAuroraModel other = (JogoAuroraModel) obj;
		return Objects.equals(cdJogo, other.cdJogo);
	}
}
