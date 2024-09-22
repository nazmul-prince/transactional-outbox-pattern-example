package com.outboxpoller.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class RabbitMqMessagePublisher implements MessagePublisher{


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${order.poller.topic.exchange.name}")
    private String topicExchangeName;

    @Value("${order.poller.routing.key}")
    private String routingKey;

    public void publish(String payload) {
        log.info("Publishing through rabbit");
        rabbitTemplate.convertAndSend(topicExchangeName, routingKey, payload);
    }
}
