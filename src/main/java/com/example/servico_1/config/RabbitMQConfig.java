package com.example.servico_1.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Nomes constantes para reutilização
    public static final String EXCHANGE_NAME = "servico1.exchange";
    public static final String ADVOGADO_QUEUE = "advogado.criado.queue";
    public static final String CLIENTE_QUEUE = "cliente.criado.queue";
    public static final String PAGAMENTO_QUEUE = "pagamento.aprovado.queue";

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue advogadoCriadoQueue() {
        return new Queue(ADVOGADO_QUEUE, true); // durable=true
    }

    @Bean
    public Queue clienteCriadoQueue() {
        return new Queue(CLIENTE_QUEUE, true);
    }

    @Bean
    public Queue pagamentoAprovadoQueue() {
        return new Queue(PAGAMENTO_QUEUE, true);
    }

    @Bean
    public Binding advogadoCriadoBinding(TopicExchange exchange, Queue advogadoCriadoQueue) {
        return BindingBuilder.bind(advogadoCriadoQueue)
                .to(exchange)
                .with("advogado.criado");
    }

    @Bean
    public Binding clienteCriadoBinding(TopicExchange exchange, Queue clienteCriadoQueue) {
        return BindingBuilder.bind(clienteCriadoQueue)
                .to(exchange)
                .with("cliente.criado");
    }

    @Bean
    public Binding pagamentoAprovadoBinding(TopicExchange exchange, Queue pagamentoAprovadoQueue) {
        return BindingBuilder.bind(pagamentoAprovadoQueue)
                .to(exchange)
                .with("pagamento.aprovado");
    }
}