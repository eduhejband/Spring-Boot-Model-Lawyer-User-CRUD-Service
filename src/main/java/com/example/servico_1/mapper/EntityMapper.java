package com.example.servico_1.mapper;

import com.example.servico_1.dto.ClienteDTO;
import com.example.servico_1.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    @Mapping(source = "advogado.id", target = "advogadoId")
    ClienteDTO toClienteDTO(Cliente cliente);

    @Mapping(source = "advogadoId", target = "advogado.id")
    Cliente toCliente(ClienteDTO clienteDTO);
}