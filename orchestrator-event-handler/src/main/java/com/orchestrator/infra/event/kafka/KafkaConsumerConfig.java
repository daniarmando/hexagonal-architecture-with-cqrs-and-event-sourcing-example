package com.orchestrator.infra.event.kafka;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import com.orchestrator.core.model.Event;

import lombok.Getter;

@Configuration
@EnableConfigurationProperties(KafkaConsumerConfig.KafkaProperties.class)
public class KafkaConsumerConfig {
	
	public static final String CONTAINER_FACTORY = "listenerFactory";
	
	@ConfigurationProperties("kafka.config")
	@Getter
	public static class KafkaProperties {
		private final Map<String, Object> consumer = new HashMap<>();
	}
	
	@Bean
	public ConsumerFactory<String, Event> consumerFactory(KafkaProperties kafkaProperties) {		
		return new DefaultKafkaConsumerFactory<>(kafkaProperties.getConsumer());
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Event> listenerFactory(KafkaProperties kafkaProperties) {
		var factory = new ConcurrentKafkaListenerContainerFactory<String, Event>();
		factory.setConsumerFactory(consumerFactory(kafkaProperties));
		return factory;
	}

}
