package com.kafka_producer_wikimedia.kafka_producer_wikimedia.service;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import lombok.NoArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangeProducer {
    private KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaChangeProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    public void sendMessage() throws InterruptedException {
        // to read real time stream data from wikimedia, we use event source
        EventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate, "wikimedia_recentchange");
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource eventSource = builder.build();
        eventSource.start();

        TimeUnit.MINUTES.sleep(10);
    }
}
