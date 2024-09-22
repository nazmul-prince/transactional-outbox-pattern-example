package com.outbox.controller;

import com.outbox.common.dto.OrderRequestDTO;
import com.outbox.order.entity.Order;
import com.outbox.repository.OutboxRepository;
import com.outbox.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/outbox")
public class OutboxController {

    @Autowired
    private OutboxRepository outboxRepository;

    @PutMapping("/{id}/set-proccessed")
    public ResponseEntity<String> updateOutBox(@PathVariable Integer id) {
        log.info("setting is processed true for outbox id: " + id);
        outboxRepository.setProcessed(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(id.toString());
    }
}
