package com.kafka_producer_wikimedia.kafka_producer_wikimedia;

import com.kafka_producer_wikimedia.kafka_producer_wikimedia.service.WikimediaChangeProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaProducerWikimediaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerWikimediaApplication.class, args);
		System.out.println("Starting Kafka producer Application........");
	}

	@Autowired
	private WikimediaChangeProducer wikimediaChangesProducer;

	@Override
	public void run(String... arg) throws InterruptedException {
		wikimediaChangesProducer.sendMessage();
	}

}
