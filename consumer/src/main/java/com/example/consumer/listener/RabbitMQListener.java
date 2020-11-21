package com.example.consumer.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {

    @RabbitListener( queues = "topic_queue" )
    public void topicListener(Message message){

        System.out.println(message);
    }
}
