package com.orchestrator.infra.event.kafka;

import com.orchestrator.core.model.Event;

public interface KafkaConsumer {
	
	void execute(Event event);

}
