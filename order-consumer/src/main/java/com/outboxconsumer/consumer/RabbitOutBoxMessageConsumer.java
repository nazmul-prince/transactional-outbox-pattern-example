package com.outboxconsumer.consumer;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.outboxconsumer.constants.OrderEvent;
import com.outboxconsumer.entity.Inventory;
import com.outboxconsumer.service.InventoryService;
import com.outboxconsumer.service.OutboxGatewayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class RabbitOutBoxMessageConsumer {

  private CountDownLatch latch = new CountDownLatch(1);
  private final InventoryService inventoryService;
  private final OutboxGatewayService outboxGatewayService;
  private final ObjectMapper objectMapper;

  public void receiveMessage(String message) throws InterruptedException, JsonProcessingException {
//    Thread.sleep(1000);
    log.info("Received <" + message + ">");

    final var map = objectMapper.readValue(message, Map.class);
    final var id = map.get("id");
    final var orderEvent = map.get("orderEvent");
    final var payload = map.get("payload");
    
    final var order = objectMapper.readValue((String) payload, Map.class);
    final var orderId = order.get("id");
    final var name = order.get("name");

    processOrder((Integer)id, (String) name, orderEvent, orderId);
  }

  private void processOrder(Integer id, String name, Object orderEvent, Object orderId) {
    if(Objects.equals(OrderEvent.CREATED.name(), orderEvent)) {
      final Inventory inventory = Inventory.builder()
              .orderId((Integer) orderId)
              .orderName((String) name)
              .build();
      inventoryService.save(inventory);
    } else {
      inventoryService.updateInventoryOnOrderChange((Integer) orderId, name);
    }

    outboxGatewayService.sendAcknowledgment(id);
  }

  public CountDownLatch getLatch() {
    return latch;
  }

}