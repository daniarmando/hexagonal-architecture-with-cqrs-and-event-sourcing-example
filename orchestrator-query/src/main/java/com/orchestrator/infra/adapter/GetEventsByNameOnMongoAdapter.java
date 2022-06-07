package com.orchestrator.infra.adapter;

import org.springframework.stereotype.Component;

import com.orchestrator.core.model.Event;
import com.orchestrator.core.port.output.GetEventsByNameOutputPort;
import com.orchestrator.infra.database.mongo.document.EventMongoDocument;
import com.orchestrator.infra.database.mongo.repository.EventRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class GetEventsByNameOnMongoAdapter implements GetEventsByNameOutputPort {

	private final EventRepository repository;
	
	@Override
	public Flux<Event> execute(String name) {	
		return repository.findByName(name).map(EventMongoDocument::toDomain);				
	}

}
