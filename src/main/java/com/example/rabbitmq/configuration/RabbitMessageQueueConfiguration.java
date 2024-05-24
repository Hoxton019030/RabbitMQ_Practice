package com.example.rabbitmq.configuration;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Configuration
public class RabbitMessageQueueConfiguration {

    private ConnectionFactory connectionFactory;
    @Value("${rabbitmq.routekey.name}")
    String routeKey;
    @Value("${rabbitmq.exchange.name}")
    String exchangeName;
    @Value("rabbitmq.queue.name")
    String queueName;

    @Bean
    public ConnectionFactory connectionFactory() {
        return connectionFactory;
    }

    @PostConstruct
    public void setupRabbitMQ() throws IOException, TimeoutException {
        // 使用 connectionFactory bean 创建连接
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setUsername("hoxton");
        connectionFactory.setPassword("123456");
        connectionFactory.setPort(5672);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT, true, false, null);

        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName
                , routeKey);

        String message = "Rabbit Mq啟動成功";
        channel.basicPublish(exchangeName, queueName, null, message.getBytes());

        channel.close();
        connection.close();
    }

}
