package com.orchestrator.core.port.output;

import com.orchestrator.core.model.Event;

import reactor.core.publisher.Mono;

public interface AddToEventStoreOutputPort {
	
	Mono<Void> execute(Event event);

}
