package com.orchestrator.core.event;

import org.springframework.stereotype.Component;

import com.orchestrator.core.model.Event;
import com.orchestrator.core.port.input.AddToEventStoreInputPort;
import com.orchestrator.core.port.output.AddToEventStoreOutputPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EventHandler implements AddToEventStoreInputPort {
	
	private final AddToEventStoreOutputPort outputPort;

	@Override
	public void execute(Event event) {
		outputPort.execute(event).block();		
	}

}
