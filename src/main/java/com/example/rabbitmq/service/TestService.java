package com.example.rabbitmq.service;

import com.example.rabbitmq.producer.RabbitMQProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestService {
    private final RabbitMQProducer rabbitMQProducer;
    public void sendMessageToRabbitMq(String message){
        log.info("發送消息到Rabbit Mq:{}",message);
        rabbitMQProducer.sendMessage(message);
    }
}
