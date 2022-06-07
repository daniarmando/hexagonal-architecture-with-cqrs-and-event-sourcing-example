package com.orchestrator.core.port.input;

import com.orchestrator.core.model.Event;

import reactor.core.publisher.Flux;

public interface GetEventsByNameInputPort {
	
	Flux<Event> execute(String name);

}
