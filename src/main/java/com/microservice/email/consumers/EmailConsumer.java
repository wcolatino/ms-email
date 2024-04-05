package com.microservice.email.consumers;

import com.microservice.email.dtos.EmailRecordDto;
import com.microservice.email.models.Email;
import com.microservice.email.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 *  Consumer - é o cara que consumirá as mensagens da fila *
 */
@Component
public class EmailConsumer {

    final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmaiLQueue(@Payload EmailRecordDto emailDto){

        var email = new Email();
        BeanUtils.copyProperties(emailDto, email);
        emailService.sendEmail(email);
    }
}
