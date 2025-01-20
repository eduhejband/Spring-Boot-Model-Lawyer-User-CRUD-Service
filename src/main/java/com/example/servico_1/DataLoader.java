package com.example.servico_1;

import com.example.servico_1.model.Advogado;
import com.example.servico_1.repository.AdvogadoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DataLoader implements CommandLineRunner {

    private final AdvogadoRepository advogadoRepository;

    public DataLoader(AdvogadoRepository advogadoRepository) {
        this.advogadoRepository = advogadoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Optional<Advogado> advogadoExistente = advogadoRepository.findByEmail("advogado@inicial.com");
        
        if (advogadoExistente.isEmpty()) {
            Advogado advogado = new Advogado();
            advogado.setNome("Advogado Inicial");
            advogado.setEmail("advogado@inicial.com");
            advogado.setIsActive(true);
            advogadoRepository.save(advogado);
        }
    }
}
