package com.example.demo.controller;

import com.example.demo.domain.Employee;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Sender가 message를 RabbitMQ에게 AmqpTmeplate을 통해 보냄
 * exchange와 exchange key를 사용한다
 *
 * exchange : message를 다른 queue에 route하는 역할. RabbitMQ 내 호스트 마다 정의
 * producer에게 message를 받아 queue로 전송
 * queue로 전송할 때 header, binding, route key의 도움을 받는다
 *
 */
@Service
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${javainuse.rabbitmq.exchange}")
    private String exchange;

    @Value("${javainuse.rabbitmq.routingkey}")
    private String routingkey;

    public void send(Employee company) {
        rabbitTemplate.convertAndSend(exchange, routingkey, company);
        System.out.println("Send msg = " + company);

    }
}