package com.example.producer.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "topic_exchange";
    public static final String QUEUE_NAME = "topic_queue";

    //exchange
    @Bean("exchange")
    public Exchange exchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }

    //queue
    @Bean("queue")
    public Queue queue(){
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    //binding
    @Bean("binding")
    public Binding binding(@Qualifier("queue") Queue queue, @Qualifier("exchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
    }
}
