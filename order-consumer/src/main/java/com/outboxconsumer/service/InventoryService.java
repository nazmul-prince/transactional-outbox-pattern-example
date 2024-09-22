package com.outboxconsumer.service;

import com.outboxconsumer.entity.Inventory;
import com.outboxconsumer.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional
    public void save(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    @Transactional
    public void updateInventoryOnOrderChange(Integer orderId, String orderName) {
        inventoryRepository.updateInventoryOnOrderChange(orderName, orderId);
    }
}
