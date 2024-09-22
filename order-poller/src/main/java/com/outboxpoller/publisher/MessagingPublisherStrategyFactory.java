package com.outboxpoller.publisher;

import com.outboxpoller.constants.MessagingTemplateType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MessagingPublisherStrategyFactory {
    @Value("${messaging.template.type}")
    private String messagingTemplateType;

    private final RabbitMqMessagePublisher rabbitMqMessagePublisher;

    private final KafkaMessagePublisher kafkaMessagePublisher;

    public void publish(String payload) {
        if(Objects.equals(MessagingTemplateType.RABBIT.name(), messagingTemplateType)) {
            rabbitMqMessagePublisher.publish(payload);
        } else {
            kafkaMessagePublisher.publish(payload);
        }
    }
}
