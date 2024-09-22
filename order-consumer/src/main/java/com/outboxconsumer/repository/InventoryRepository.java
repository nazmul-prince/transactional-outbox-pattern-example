package com.outboxconsumer.repository;

import com.outboxconsumer.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    @Modifying
    @Query(value = """
            update inventory set order_name = ?1
                where order_id = ?2
            """, nativeQuery = true)
    void updateInventoryOnOrderChange(String orderName, Integer orderId);
}
