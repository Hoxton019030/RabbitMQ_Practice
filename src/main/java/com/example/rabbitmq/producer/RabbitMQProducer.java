package com.example.rabbitmq.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;
    String exchangeName = "xc_exchange_name";
    public void sendMessage(String message) {
        System.out.println("呼叫到Producer");
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }
}
