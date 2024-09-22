package com.outboxconsumer.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaOutBoxMessageConsumer {


//    @KafkaListener(topics = "unprocessed-order-events",groupId = "jt-group")
//    public void consume(String payload){
//        log.info("Event consumed {} ",payload);
//    }
}
