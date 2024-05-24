package com.example.rabbitmq.comsumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class RabbitConsumer {

    /**
     * 監聽隊列：當隊列中有消息，則監聽器工作，處理接收到的訊息
     * @param message
     */

    @RabbitListener(queues = "xc_queue_name")
    public void process(Message message) {
        byte[] body = message.getBody();
        System.out.println("呼叫到Consumer");
        System.out.println("接收到的消息" + new String(body));

    }
}


