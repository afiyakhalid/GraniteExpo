package com.graniteexpo.demo.outbox;

public interface  OutboxPublisher {

    // This sends the event to your message broker (Kafka/RabbitMQ).
    // If it throws an exception, outbox processor will retry later.
    void publish(String eventType, String payloadJson);
}
