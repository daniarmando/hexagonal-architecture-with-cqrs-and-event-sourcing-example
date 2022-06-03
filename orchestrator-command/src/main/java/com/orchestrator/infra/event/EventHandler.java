package com.orchestrator.infra.event;

public interface EventHandler<T extends Event<?>> {
	
	void handle(T event);

}
