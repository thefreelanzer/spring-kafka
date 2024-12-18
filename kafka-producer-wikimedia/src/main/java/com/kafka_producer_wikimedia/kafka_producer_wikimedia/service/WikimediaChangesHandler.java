package com.kafka_producer_wikimedia.kafka_producer_wikimedia.service;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;


public class WikimediaChangesHandler implements EventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesHandler.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic;

    @Autowired
    public WikimediaChangesHandler(KafkaTemplate<String, String> kafkaTemplate, String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public void onOpen() throws Exception {
        // Connection open logic (if needed)
        LOGGER.info("Connection opened.");
    }

    @Override
    public void onClosed() throws Exception {
        // Connection closed logic (if needed)
        LOGGER.info("Connection closed.");
    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        String messageData = messageEvent.getData();
        LOGGER.info("Received message: {}", messageData);

        // Sending the message to Kafka
        try {
            kafkaTemplate.send(topic, messageData);
            LOGGER.info("Message sent to Kafka topic: {}", topic);
        } catch (Exception e) {
            LOGGER.error("Error sending message to Kafka", e);
        }
    }

    @Override
    public void onComment(String s) throws Exception {
        // Handle comments in the SSE stream if needed
    }

    @Override
    public void onError(Throwable throwable) {
        LOGGER.error("Error in SSE stream", throwable);
    }
}
