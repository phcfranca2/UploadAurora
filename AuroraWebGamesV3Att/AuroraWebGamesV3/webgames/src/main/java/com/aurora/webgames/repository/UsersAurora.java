package com.aurora.webgames.repository;

import java.util.List;

import com.aurora.webgames.model.UsuarioAuroraModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsersAurora extends JpaRepository<UsuarioAuroraModel, Long>{
    
    UsuarioAuroraModel findByNome(String nome);
}
