package com.orchestrator.infra.database.mongo.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.orchestrator.infra.database.mongo.document.EventMongoDocument;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface EventRepository extends ReactiveMongoRepository<EventMongoDocument, String> {

	Mono<EventMongoDocument> findById(UUID id);
	
	Flux<EventMongoDocument> findByName(String name);
	
}
