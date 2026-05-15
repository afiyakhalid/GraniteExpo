package com.graniteexpo.demo.outbox;

import org.springframework.stereotype.Component;

@Component
public class LoggingOutboxPublisher implements OutboxPublisher {

    @Override
    public void publish(String eventType, String payloadJson) {
        // For now, just log. Later replace with KafkaTemplate / RabbitTemplate.
        System.out.println("[OUTBOX-PUBLISH] type=" + eventType + " payload=" + payloadJson);
    }
}