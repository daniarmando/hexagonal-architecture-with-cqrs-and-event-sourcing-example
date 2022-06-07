package com.orchestrator.core.usecase;

import com.orchestrator.core.model.Event;
import com.orchestrator.core.port.input.GetEventsByNameInputPort;
import com.orchestrator.core.query.GetEventsByNameQuery;
import com.orchestrator.infra.annotation.UseCase;
import com.orchestrator.infra.query.QueryBus;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@UseCase
@RequiredArgsConstructor
public class GetEventsByNameUseCase implements GetEventsByNameInputPort {
	
	private final QueryBus queryBus;

	@Override
	public Flux<Event> execute(String name) {
		return queryBus.handle(new GetEventsByNameQuery(name));
	}

}
