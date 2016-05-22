package com.bootdemo.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ReceiverService {

    @RabbitListener(queues = "queue1")
    public void receiveFooQueue(String queue1) {
        System.out.println("Received Foo<" + queue1 + ">");
    }

    @RabbitListener(queues = "queue2")
    public void receiveBarQueue(String  queue2) {
        System.out.println("Received Bar<" + queue2 + ">");
    }
}