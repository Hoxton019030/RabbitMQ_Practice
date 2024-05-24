package com.example.rabbitmq.comsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class RabbitConsumer {

    /**
     * 監聽隊列：當隊列中有消息，則監聽器工作，處理接收到的訊息
     * @param message
     */

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void process(Message message)  {
        byte[] body = message.getBody();
        log.info("呼叫到Consumer");
        log.info("接收到的消息" + new String(body));
    }
}


