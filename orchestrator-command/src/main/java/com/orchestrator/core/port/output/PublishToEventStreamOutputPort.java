package com.orchestrator.core.port.output;

import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import com.orchestrator.infra.event.EventStreaming;

public interface PublishToEventStreamOutputPort {
	
	ListenableFuture<SendResult<String, EventStreaming>> send(EventStreaming event);
	
	void error(EventStreaming event);

}
