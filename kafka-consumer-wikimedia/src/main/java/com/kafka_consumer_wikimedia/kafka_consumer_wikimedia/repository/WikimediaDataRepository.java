package com.kafka_consumer_wikimedia.kafka_consumer_wikimedia.repository;

import com.kafka_consumer_wikimedia.kafka_consumer_wikimedia.entity.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaDataRepository extends JpaRepository<WikimediaData, Long> {
}
