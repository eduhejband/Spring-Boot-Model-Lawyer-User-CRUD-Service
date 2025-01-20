package com.example.servico_1.controller;

import com.example.servico_1.dto.ClienteDTO;
import com.example.servico_1.exception.ResourceNotFoundException;
import com.example.servico_1.model.Advogado;
import com.example.servico_1.service.ClienteService;
import com.example.servico_1.service.AdvogadoService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/advogados")
public class AdvogadoController {

    private final ClienteService clienteService;
    private final AdvogadoService advogadoService;

    public AdvogadoController(ClienteService clienteService, AdvogadoService advogadoService) {
        this.clienteService = clienteService;
        this.advogadoService = advogadoService;
    }

    @PostMapping("/clientes")
    public ResponseEntity<?> cadastrarCliente(@RequestBody @Valid ClienteDTO clienteDTO, @RequestParam Long advogadoId) {
        Advogado advogado = advogadoService.buscarPorId(advogadoId)
                .orElseThrow(() -> new ResourceNotFoundException("Advogado não encontrado.")); // Usa exceção customizada
        ClienteDTO clienteCadastrado = clienteService.cadastrarCliente(clienteDTO, advogado);
        return ResponseEntity.ok(clienteCadastrado);
    }

    @GetMapping("/clientes/{advogadoId}")
    public ResponseEntity<?> listarClientes(@PathVariable Long advogadoId) {
        return ResponseEntity.ok(clienteService.listarClientesPorAdvogado(advogadoId));
    }
    
    @PostMapping
    public ResponseEntity<Advogado> cadastrarAdvogado(@RequestBody Advogado advogado) {
        Advogado advogadoSalvo = advogadoService.cadastrarAdvogado(advogado);
        return ResponseEntity.ok(advogadoSalvo);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Advogado> buscarAdvogadoPorId(@PathVariable Long id) {
        Advogado advogado = advogadoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Advogado não encontrado."));
        return ResponseEntity.ok(advogado);
    }

}