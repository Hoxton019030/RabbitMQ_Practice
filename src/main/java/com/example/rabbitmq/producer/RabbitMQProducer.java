package com.example.rabbitmq.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.routekey.name}")
    String routeKey;
    @Value("${rabbitmq.exchange.name}")
    String exchangeName;
    @Value("rabbitmq.queue.name")
    String queueName;


    public void sendMessage(String message) {
        log.info("呼叫到MessageQueueProducer");
        rabbitTemplate.convertAndSend(exchangeName, routeKey, message);
    }
}
