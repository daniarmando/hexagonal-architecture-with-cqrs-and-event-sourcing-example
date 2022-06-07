package com.orchestrator.core.query;

import com.orchestrator.core.model.Event;
import com.orchestrator.infra.query.Query;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import reactor.core.publisher.Flux;

@Getter
@ToString
@RequiredArgsConstructor
public class GetEventsByNameQuery extends Query<Flux<Event>> {
	
	private final String name;

}
