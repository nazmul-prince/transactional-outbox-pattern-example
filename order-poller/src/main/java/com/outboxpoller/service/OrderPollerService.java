package com.outboxpoller.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.outboxpoller.entity.Outbox;
import com.outboxpoller.publisher.KafkaMessagePublisher;
import com.outboxpoller.publisher.MessagingPublisherStrategyFactory;
import com.outboxpoller.repository.OutboxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableScheduling
@Slf4j
public class OrderPollerService {

    @Autowired
    private OutboxRepository repository;

    @Autowired
    private MessagingPublisherStrategyFactory publisherStrategyFactory;

    @Autowired
    private ObjectMapper objectMapper;


    @Scheduled(fixedRate = 10000)
    public void pollOutboxMessagesAndPublish() {

        //1. fetch unprocessed record
        List<Outbox> unprocessedRecords = repository.findByProcessedFalse();

        log.info("unprocessed record count : {}", unprocessedRecords.size());

        //2. publish record to kafka/queue

        unprocessedRecords.forEach(outbox -> {
            try {
                log.info("processing {} with payload {}", outbox.getId(), outbox.getPayload());
                final var s = objectMapper.writeValueAsString(outbox);
                publisherStrategyFactory.publish(s);
                //update the message status to processed=true to avoid duplicate message processing
                log.info("saving {} with payload {}", outbox.getId(), outbox.getPayload());
//                outbox.setProcessed(true);
//                repository.save(outbox);

            } catch (Exception ignored) {
                log.error(ignored.getMessage());
            }
        });


    }
}
