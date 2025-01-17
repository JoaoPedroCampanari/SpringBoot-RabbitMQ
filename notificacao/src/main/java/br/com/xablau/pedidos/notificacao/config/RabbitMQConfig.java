package br.com.xablau.pedidos.notificacao.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.exchange.dlx.name}")
    private String exchangeDlxName;

    @Value("${rabbitmq.queue.dlq.name}")
    private String queueDlqName;

    @Value("${rabbit.direct.exchange.name}")
    private String exchangePedidoProcessamento;

    @Value("${rabbit.processo.queue.name}")
    private String queueNotificacaoProcessamento;

    @Bean
    public FanoutExchange pedidosExchange() {
        return new FanoutExchange(exchangeName);
    }

    @Bean
    public FanoutExchange pedidosDlxExchange() {
        return new FanoutExchange(exchangeDlxName);
    }

    @Bean
    public DirectExchange pedidosProcessamentoExchange() {
        return new DirectExchange(exchangePedidoProcessamento);
    }

    @Bean
    public Queue notificacaoQueue() {
        Map<String, Object> argumentos = new HashMap<>();
        argumentos.put("x-dead-letter-exchange", exchangeDlxName);
        return new Queue(queueName, true, false, false, argumentos);
    }

    @Bean
    public Queue notificacaoDlqQueue() {
        return new Queue(queueDlqName);
    }

    @Bean
    public Queue notificacaoPedidoProcessadoQueue() {
        return new Queue(queueNotificacaoProcessamento);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(notificacaoQueue()).to(pedidosExchange());
    }

    @Bean
    public Binding bindingDlxDlq(){
        return BindingBuilder.bind(notificacaoDlqQueue()).to(pedidosDlxExchange());
    }

    @Bean
    public Binding bindingPedidoProcessado(){
        return BindingBuilder.bind(notificacaoPedidoProcessadoQueue()).to(pedidosProcessamentoExchange()).with("processamento");
    }


    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> listener(RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize();
    }
}
