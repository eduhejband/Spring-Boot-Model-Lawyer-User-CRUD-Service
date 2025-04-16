package com.example.servico_1.service;

import com.example.servico_1.event.AdvogadoCriadoEvent;
import com.example.servico_1.event.PagamentoAprovadoEvent;
import com.example.servico_1.messaging.MessagePublisher;
import com.example.servico_1.model.Advogado;
import com.example.servico_1.repository.AdvogadoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AdvogadoService {

    private final AdvogadoRepository advogadoRepository;

    public AdvogadoService(AdvogadoRepository advogadoRepository, MessagePublisher messagePublisher) {
        this.advogadoRepository = advogadoRepository;
        this.messagePublisher = messagePublisher;
    }

    public Optional<Advogado> buscarPorId(Long id) {
        return advogadoRepository.findById(id);
    }

    // Adicione no início da classe:
    private final MessagePublisher messagePublisher;

    // Modifique o método cadastrarAdvogado:
    public Advogado cadastrarAdvogado(Advogado advogado) {
        if (advogadoRepository.findByEmail(advogado.getEmail()).isPresent()) {
            throw new IllegalStateException("Já existe um advogado com esse e-mail.");
        }
        advogado.setIsActive(true);
        Advogado saved = advogadoRepository.save(advogado);

        messagePublisher.publishAdvogadoCriado(
                new AdvogadoCriadoEvent(
                        saved.getId(),
                        saved.getNome(),
                        saved.getEmail(),
                        saved.getCreatedAt()
                )
        );

        return saved;
    }

    // Modifique o método aprovarPagamento:
    public void aprovarPagamento(Long id) {
        Advogado advogado = advogadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Advogado não encontrado."));
        advogado.setHasPaid(true);
        advogadoRepository.save(advogado);

        messagePublisher.publishPagamentoAprovado(
                new PagamentoAprovadoEvent(id, LocalDateTime.now())
        );
    }

}
