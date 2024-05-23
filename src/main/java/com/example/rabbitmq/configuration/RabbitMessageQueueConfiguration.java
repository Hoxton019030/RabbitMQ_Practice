package com.example.rabbitmq.configuration;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Configuration
public class RabbitMessageQueueConfiguration {

    private ConnectionFactory connectionFactory;

    @Bean
    public ConnectionFactory connectionFactory() {
        return connectionFactory;
    }

    @PostConstruct
    public void setupRabbitMQ() throws IOException, TimeoutException {
        // 使用 connectionFactory bean 创建连接
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setPort(5672);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "xc_exchange_name";
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT, true, false, null);

        String queueName = "xc_queue_name";
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, queueName);

        String message = "Hello rabbitMQ";
        channel.basicPublish(exchangeName, queueName, null, message.getBytes());

        channel.close();
        connection.close();
    }
}
