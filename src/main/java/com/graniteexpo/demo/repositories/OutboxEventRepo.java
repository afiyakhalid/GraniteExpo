package com.graniteexpo.demo.repositories;

import com.graniteexpo.demo.entities.OutboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface OutboxEventRepo extends JpaRepository<OutboxEvent, UUID> {

    // Fetches events that are ready to publish.
    // Explanation:
    // - status = 'PENDING' means not yet published
    // - nextAttemptAt <= now means it’s due to be retried/published now
    // - order by createdAt so older events get processed first
    @Query("""
        select e from OutboxEvent e
        where e.status = 'PENDING'
          and e.nextAttemptAt <= ?1
        order by e.createdAt asc
    """)
    List<OutboxEvent> findReadyToPublish(OffsetDateTime now);
}
