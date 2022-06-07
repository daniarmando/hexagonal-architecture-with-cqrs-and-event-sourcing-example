package com.orchestrator.core.query.handler;

import com.orchestrator.core.model.Event;
import com.orchestrator.core.port.output.GetEventsByNameOutputPort;
import com.orchestrator.core.query.GetEventsByNameQuery;
import com.orchestrator.infra.annotation.Handler;
import com.orchestrator.infra.query.QueryHandler;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Handler
@RequiredArgsConstructor
public class GetEventsByNameQueryHandler implements QueryHandler<Flux<Event>, GetEventsByNameQuery> {
	
	private final GetEventsByNameOutputPort outputPort;

	@Override
	public Flux<Event> handle(GetEventsByNameQuery query) {
		return outputPort.execute(query.getName());
	}

}
