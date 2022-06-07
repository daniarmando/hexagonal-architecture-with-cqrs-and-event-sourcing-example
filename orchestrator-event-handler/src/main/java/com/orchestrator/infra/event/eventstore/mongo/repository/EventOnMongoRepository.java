package com.orchestrator.infra.event.eventstore.mongo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.orchestrator.infra.event.eventstore.mongo.document.EventMongoDocument;

@Repository
public interface EventOnMongoRepository extends ReactiveMongoRepository<EventMongoDocument, String> {

}
