package com.example.servico_1.service;

import com.example.servico_1.model.Advogado;
import com.example.servico_1.repository.AdvogadoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdvogadoService {

    private final AdvogadoRepository advogadoRepository;

    public AdvogadoService(AdvogadoRepository advogadoRepository) {
        this.advogadoRepository = advogadoRepository;
    }

    public Optional<Advogado> buscarPorId(Long id) {
        return advogadoRepository.findById(id);
    }
    
    public Advogado cadastrarAdvogado(Advogado advogado) {
        if (advogadoRepository.findByEmail(advogado.getEmail()).isPresent()) {
            throw new IllegalStateException("Já existe um advogado com esse e-mail.");
        }
        advogado.setIsActive(true);
        return advogadoRepository.save(advogado);
    }


    public void aprovarPagamento(Long id) {
        Advogado advogado = advogadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Advogado não encontrado."));
        advogado.setHasPaid(true); // Marca como pago
        advogadoRepository.save(advogado); // Salva a alteração no banco
    }

}
