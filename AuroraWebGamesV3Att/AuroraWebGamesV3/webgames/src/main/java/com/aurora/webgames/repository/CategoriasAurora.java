package com.aurora.webgames.repository;

import com.aurora.webgames.model.CadastroAuroraModel;
import com.aurora.webgames.model.JogoAuroraModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface CategoriasAurora extends JpaRepository<CadastroAuroraModel, Long>{
	
}
