package com.orchestrator.infra.event;

public interface EventBus {
	
	void handle(Event<?> event);
	
	void publishError(Event<?> event, Throwable throwable);
	
	void publishError(Class<?> eventType, String key, Object data, Throwable throwable);
    
}
