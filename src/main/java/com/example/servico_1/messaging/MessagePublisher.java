package com.example.servico_1.messaging;

import com.example.servico_1.config.RabbitMQConfig;
import com.example.servico_1.event.AdvogadoCriadoEvent;
import com.example.servico_1.event.ClienteCriadoEvent;
import com.example.servico_1.event.PagamentoAprovadoEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessagePublisher {
    private final RabbitTemplate rabbitTemplate;

    public MessagePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishAdvogadoCriado(AdvogadoCriadoEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                "advogado.criado",
                event
        );
    }

    public void publishClienteCriado(ClienteCriadoEvent event) {
        rabbitTemplate.convertAndSend(
                "cliente.exchange",
                "cliente.criado",
                event
        );
    }

    public void publishPagamentoAprovado(PagamentoAprovadoEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                "pagamento.aprovado",
                event
        );
    }
}