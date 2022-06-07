package com.orchestrator.core.port.input;

import com.orchestrator.core.model.Event;

public interface AddToEventStoreInputPort {
	
	void execute(Event event);

}
