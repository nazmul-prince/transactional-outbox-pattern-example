package com.outboxconsumer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class OutboxGatewayService {

    @Autowired
    private RestClient restClient;

    public void sendAcknowledgment(Integer outboxId) {
        final ResponseEntity<Void> responseEntity = restClient.put()
                .uri("http://localhost:9191/api/outbox/" + outboxId + "/set-proccessed")
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(status -> status.value() == 404, (request, response) -> {
                    log.error(response.getStatusCode().toString());
                })
                .toBodilessEntity();
        log.info("response: " + responseEntity.getStatusCode());
    }
}