package com.example.rabbitmq.controller;

import com.example.rabbitmq.producer.RabbitMQProducer;
import com.example.rabbitmq.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rabbit")
@RequiredArgsConstructor
public class TestController {

    private final RabbitMQProducer rabbitMQProducer;
    private final TestService testService;

    @GetMapping("/")
    public ResponseEntity<String> sendMessage() {
        testService.sendMessageToRabbitMq("我是來自前端的訊息");
        return ResponseEntity.ok().body("完成");
    }
}

