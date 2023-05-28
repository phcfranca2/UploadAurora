package com.aurora.webgames.repository;

import com.aurora.webgames.model.JogoAuroraModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JogosAurora extends JpaRepository<JogoAuroraModel, Long>{
	
    
}
