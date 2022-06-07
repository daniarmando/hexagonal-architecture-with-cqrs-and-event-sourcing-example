package com.orchestrator.core.port.output;

import com.orchestrator.core.model.Event;

import reactor.core.publisher.Flux;

public interface GetEventsByNameOutputPort {
	
	Flux<Event> execute(String name);

}
