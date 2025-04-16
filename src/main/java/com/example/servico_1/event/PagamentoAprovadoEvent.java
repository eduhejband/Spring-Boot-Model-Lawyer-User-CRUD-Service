package com.example.servico_1.event;

import java.io.Serializable;
import java.time.LocalDateTime;

public record PagamentoAprovadoEvent(
        Long advogadoId,
        LocalDateTime dataAprovacao
) implements Serializable {}