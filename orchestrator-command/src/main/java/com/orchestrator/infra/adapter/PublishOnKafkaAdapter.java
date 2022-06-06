package com.orchestrator.infra.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import com.orchestrator.core.port.output.PublishToEventStreamOutputPort;
import com.orchestrator.infra.annotation.Adapter;
import com.orchestrator.infra.event.EventStreaming;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter
@RequiredArgsConstructor
public class PublishOnKafkaAdapter implements PublishToEventStreamOutputPort { 
	
	private final KafkaTemplate<String, EventStreaming> kafkaTemplate;
	
	@Value("${kafka.config.topic.producer}")
	private String topic;
	
	@Override
	public ListenableFuture<SendResult<String, EventStreaming>> send(EventStreaming event) {		
		log.info("Sending event {} to kakfa topic {} key {}", event, topic, event.getKey());		
		return kafkaTemplate.send(topic, event.getKey(), event);
	}

	@Override
	public void error(EventStreaming event) {
		log.info("Sending error event {} to kakfa topic {} key {}", event, topic, event.getKey());
		kafkaTemplate.send(topic, event.getKey(), event);
	}

}
