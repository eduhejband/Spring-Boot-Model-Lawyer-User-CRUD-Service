package com.example.servico_1.event;

import java.io.Serializable;
import java.time.LocalDateTime;

public record AdvogadoCriadoEvent(
        Long id,
        String nome,
        String email,
        LocalDateTime createdAt
) implements Serializable {} // Adicione implements Serializable