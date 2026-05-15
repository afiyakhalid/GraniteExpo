package com.graniteexpo.demo.outbox;

import com.graniteexpo.demo.entities.OutboxEvent;
import com.graniteexpo.demo.repositories.OutboxEventRepo;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class OutboxService {

    private final OutboxEventRepo outboxRepo;

    public OutboxService(OutboxEventRepo outboxRepo) {
        this.outboxRepo = outboxRepo;
    }

    public UUID enqueueEvent(String aggregateType,
                             UUID aggregateId,
                             String eventType,
                             String payloadJson) {

        // This method just writes a DB row.
        // It does NOT talk to Kafka/RabbitMQ directly.
        // That is what makes it reliable.

        OutboxEvent e = new OutboxEvent();
        e.setId(UUID.randomUUID());
        e.setAggregateType(aggregateType);
        e.setAggregateId(aggregateId);
        e.setEventType(eventType);
        e.setPayload(payloadJson);

        e.setStatus("PENDING");
        e.setAttempts(0);
        e.setNextAttemptAt(OffsetDateTime.now());
        e.setCreatedAt(OffsetDateTime.now());

        outboxRepo.save(e);
        return e.getId();
    }
}
