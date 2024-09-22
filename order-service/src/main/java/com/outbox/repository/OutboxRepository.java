package com.outbox.repository;

import com.outbox.entity.Outbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OutboxRepository extends JpaRepository<Outbox,Long> {

    @Modifying
    @Transactional
    @Query(value = """
            update Outbox set processed = 1 where id = ?1
            """, nativeQuery = true)
    public void setProcessed(Integer id);
}
