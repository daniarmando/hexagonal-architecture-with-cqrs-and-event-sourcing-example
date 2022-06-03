package com.orchestrator.infra.command;

import reactor.core.publisher.Mono;

public interface CommandBus {
	
	Mono<Void> handle(Command command);

}
