package com.microservice.email.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${broker.queue.email.name}") //NOme definido no applications.properties
    private String queue;

    //Bean declarando a fila
    @Bean
    public Queue queue(){
        return new Queue(queue, true); //Passa o nome da fila e durable = true, assim, se o servidor cair a fila será preservada;
    }

    //Método para converter o JSON para EmailRecordDto
    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}
