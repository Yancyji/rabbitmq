package com.example.producer;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class SimpleDemo {

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException, TimeoutException {

        //1.创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        //2.设置参数
        factory.setVirtualHost("/");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("192.168.217.128");

        //3.获取连接
        Connection connection = factory.newConnection();

        //4.创建channel
        Channel channel = connection.createChannel();

        //创建队列
        String queue = "queue_demo";
        channel.queueDeclare(queue,true,false,false,null);

        //发送消息
        String body = "hello_world";
        channel.basicPublish("",queue,null,body.getBytes());

        channel.close();
        connection.close();

    }
}
