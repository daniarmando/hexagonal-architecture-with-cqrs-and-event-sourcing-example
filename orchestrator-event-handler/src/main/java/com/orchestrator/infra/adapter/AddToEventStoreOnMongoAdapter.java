package com.orchestrator.infra.adapter;

import org.springframework.stereotype.Component;

import com.orchestrator.core.model.Event;
import com.orchestrator.core.port.output.AddToEventStoreOutputPort;
import com.orchestrator.infra.event.eventstore.mongo.document.EventMongoDocument;
import com.orchestrator.infra.event.eventstore.mongo.repository.EventOnMongoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class AddToEventStoreOnMongoAdapter implements AddToEventStoreOutputPort {

	private final EventOnMongoRepository repository;
	
	@Override
	public Mono<Void> execute(Event event) {
		log.info("Adding event to event store with data {}", event);
		return repository.save(EventMongoDocument.fromDomain(event)).then();
	}

}
