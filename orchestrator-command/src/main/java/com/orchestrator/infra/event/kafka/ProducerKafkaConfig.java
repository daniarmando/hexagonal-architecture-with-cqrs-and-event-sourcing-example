package com.orchestrator.infra.event.kafka;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.orchestrator.infra.event.EventStreaming;

import lombok.Getter;

@Configuration
@EnableConfigurationProperties(ProducerKafkaConfig.KafkaProperties.class)
public class ProducerKafkaConfig {

	@Getter	
	@ConfigurationProperties("kafka.config")	
	public static class KafkaProperties {
		private final Map<String, Object> producer = new HashMap<>();
	}

	@Bean
	public ProducerFactory<String, EventStreaming> eventFactory(KafkaProperties kafkaProperties) {
		return new DefaultKafkaProducerFactory<>(kafkaProperties.getProducer());
	}

	@Bean
	public KafkaTemplate<String, EventStreaming> template(KafkaProperties kafkaProperties) {
		return new KafkaTemplate<>(eventFactory(kafkaProperties));
	}
		
}
