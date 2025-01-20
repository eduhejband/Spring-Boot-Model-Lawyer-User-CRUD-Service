package com.example.servico_1.controller;

import com.example.servico_1.service.AdvogadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/advogados")
public class AdminController {

    private final AdvogadoService advogadoService;

    public AdminController(AdvogadoService advogadoService) {
        this.advogadoService = advogadoService;
    }

    @PatchMapping("/{id}/approve-payment")
    public ResponseEntity<?> aprovarPagamento(@PathVariable Long id) {
        advogadoService.aprovarPagamento(id);
        return ResponseEntity.ok("Pagamento aprovado com sucesso.");
    }
}

