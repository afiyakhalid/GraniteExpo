package com.graniteexpo.demo.outbox;

import com.graniteexpo.demo.entities.OutboxEvent;
import com.graniteexpo.demo.repositories.OutboxEventRepo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Component
public class OutboxProcessor {

    private final OutboxEventRepo outboxRepo;
    private final OutboxPublisher publisher;

    public OutboxProcessor(OutboxEventRepo outboxRepo, OutboxPublisher publisher) {
        this.outboxRepo = outboxRepo;
        this.publisher = publisher;
    }

    @Scheduled(fixedDelay = 3000)
    @Transactional
    public void publishPending() {
        // 1) Fetch events ready to publish
        List<OutboxEvent> events = outboxRepo.findReadyToPublish(OffsetDateTime.now());

        for (OutboxEvent e : events) {
            try {
                // 2) Publish to broker (or logs for now)
                publisher.publish(e.getEventType(), e.getPayload());

                // 3) Mark as published so it won’t be sent again
                e.setStatus("PUBLISHED");
                e.setPublishedAt(OffsetDateTime.now());
                e.setLastError(null);
            } catch (Exception ex) {
                // 4) If publish fails, keep it pending and retry later
                e.setAttempts(e.getAttempts() + 1);
                e.setLastError(ex.getMessage());

                // basic retry backoff: retry after 10 seconds
                e.setNextAttemptAt(OffsetDateTime.now().plusSeconds(10));

                // optionally: after N attempts, mark FAILED
                if (e.getAttempts() >= 10) {
                    e.setStatus("FAILED");
                }
            }
        }

        // Because we're inside @Transactional, updates to entities are persisted automatically.
    }
}
