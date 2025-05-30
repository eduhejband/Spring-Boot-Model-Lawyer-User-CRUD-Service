package com.example.servico_1.service;

import com.example.servico_1.dto.ClienteDTO;
import com.example.servico_1.event.ClienteCriadoEvent;
import com.example.servico_1.mapper.EntityMapper;
import com.example.servico_1.messaging.MessagePublisher;
import com.example.servico_1.model.Advogado;
import com.example.servico_1.model.Cliente;
import com.example.servico_1.repository.AdvogadoRepository;
import com.example.servico_1.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final AdvogadoRepository advogadoRepository;
    private final EntityMapper entityMapper;

    public ClienteService(ClienteRepository clienteRepository,
                          AdvogadoRepository advogadoRepository,
                          EntityMapper entityMapper, MessagePublisher messagePublisher) {
        this.clienteRepository = clienteRepository;
        this.advogadoRepository = advogadoRepository;
        this.entityMapper = entityMapper;
        this.messagePublisher = messagePublisher;
    }

    // Adicione no início da classe:
    private final MessagePublisher messagePublisher;

    // Modifique o método cadastrarCliente:
    public ClienteDTO cadastrarCliente(ClienteDTO clienteDTO, Advogado advogado) {
        if (!advogado.getIsActive()) {
            throw new IllegalStateException("Advogado inativo ou inexistente.");
        }

        Cliente cliente = entityMapper.toCliente(clienteDTO);
        cliente.setAdvogado(advogado);
        cliente.setIsActive(true);

        Cliente clienteSalvo = clienteRepository.save(cliente);

        messagePublisher.publishClienteCriado(
                new ClienteCriadoEvent(
                        clienteSalvo.getId(),
                        clienteSalvo.getNome(),
                        clienteSalvo.getEmail(),
                        advogado.getId(),
                        clienteSalvo.getCreatedAt()
                )
        );

        return entityMapper.toClienteDTO(clienteSalvo);
    }

    public List<ClienteDTO> listarClientesPorAdvogado(Long advogadoId) {
        if (!advogadoRepository.existsById(advogadoId)) {
            throw new IllegalStateException("Advogado não encontrado.");
        }

        return clienteRepository.findByAdvogadoId(advogadoId).stream()
                .map(entityMapper::toClienteDTO)
                .collect(Collectors.toList());
    }
}


