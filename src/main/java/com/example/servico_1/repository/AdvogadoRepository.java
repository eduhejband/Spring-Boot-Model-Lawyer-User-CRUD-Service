package com.example.servico_1.repository;

import com.example.servico_1.model.Advogado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdvogadoRepository extends JpaRepository<Advogado, Long> {
    Optional<Advogado> findByEmail(String email);
}
