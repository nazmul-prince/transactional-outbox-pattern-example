package com.outboxpoller.publisher;

public interface MessagePublisher {
    public void publish(String payload);
}
