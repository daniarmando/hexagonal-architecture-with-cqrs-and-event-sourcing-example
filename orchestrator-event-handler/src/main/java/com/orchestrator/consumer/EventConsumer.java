package com.orchestrator.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.orchestrator.core.model.Event;
import com.orchestrator.core.port.input.AddToEventStoreInputPort;
import com.orchestrator.infra.event.kafka.KafkaConsumer;
import com.orchestrator.infra.event.kafka.KafkaConsumerConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventConsumer implements KafkaConsumer {
	
	private static final String TOPIC_TO_CONSUME = "${kafka.config.topic.consumer}";
	private static final String GROUP_ID = "${kafka.config.topic.group}";
	
	private final AddToEventStoreInputPort inputPort;
	
	@Value(TOPIC_TO_CONSUME)
	private String topicName;
	
	@Override
	@KafkaListener(
			topics = TOPIC_TO_CONSUME,
			groupId = GROUP_ID,
			containerFactory = KafkaConsumerConfig.CONTAINER_FACTORY)
	public void execute(@Payload Event event) {
		
		log.info("Handle event {} consumed in kakfa topic {}", event, topicName);						
		inputPort.execute(event);
		
	}	

}
