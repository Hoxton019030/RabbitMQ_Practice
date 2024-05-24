package com.example.rabbitmq.comsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;


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

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void saveMessage(Message message) {
        byte[] body = message.getBody();
        String messageContent = new String(body);
        System.out.println("呼叫到Consumer");
        System.out.println("接收到的消息: " + messageContent);

        try {
            saveMessageToFile(messageContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RabbitListener(queues = "${rabbitmq.savequeue.name}")
    private void saveMessageToFile(String messageContent) throws IOException {
        String fileName = "./message.txt";  // 替換為你需要的目錄路徑
        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            fileWriter.write(messageContent + System.lineSeparator());
        }
    }
}


