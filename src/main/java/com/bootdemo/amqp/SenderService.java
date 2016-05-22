package com.bootdemo.amqp;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SenderService {

    @Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;

    public void sendFoo2Rabbitmq(final String foo) {
        this.rabbitMessagingTemplate.convertAndSend("exchange", "queue1", foo);
    }

    public void sendBar2Rabbitmq(final String bar){
        this.rabbitMessagingTemplate.convertAndSend("exchange", "queue2", bar);
    }
}