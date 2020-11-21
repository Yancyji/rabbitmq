package com.example.producer;


import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class SimpleDemo {

    @Test
    public void fanoutTest() throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("192.168.217.128");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        //创建交换机
        String  exchange = "fanout_test";
        channel.exchangeDeclare(exchange, BuiltinExchangeType.FANOUT,true,false,false,null);

        String queue1 = "fanout_queue1";
        String queue2 = "fanout_queue2";
        channel.queueDeclare(queue1,true,false,false,null);
        channel.queueDeclare(queue2,true,false,false,null);

        channel.queueBind(queue1,exchange,"");
        channel.queueBind(queue2,exchange,"");

        String body = "info:hello world,level:info";
        channel.basicPublish(exchange,"",null,body.getBytes());

        channel.close();
        connection.close();


    }
}
