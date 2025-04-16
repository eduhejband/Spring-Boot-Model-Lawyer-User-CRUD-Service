package com.example.servico_1.event;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ClienteCriadoEvent(
        Long id,
        String nome,
        String email,
        Long advogadoId,
        LocalDateTime createdAt
) implements Serializable {}