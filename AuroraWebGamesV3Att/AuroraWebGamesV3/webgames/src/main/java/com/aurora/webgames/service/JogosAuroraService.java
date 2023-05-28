package com.aurora.webgames.service;

import java.util.List;

import com.aurora.webgames.model.CadastroAuroraModel;
import com.aurora.webgames.model.JogoAuroraModel;
import com.aurora.webgames.repository.JogosAurora;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JogosAuroraService {
    
    @Autowired
    private JogosAurora jogosAurora;
}