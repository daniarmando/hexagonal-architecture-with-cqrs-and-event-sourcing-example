package com.orchestrator.core.event.handler;

import org.springframework.web.reactive.function.client.WebClientException;

import com.orchestrator.core.event.BookingCreatedEvent;
import com.orchestrator.infra.annotation.Handler;
import com.orchestrator.infra.event.EventHandler;

@Handler
public class BookingCreatedEventHandler implements EventHandler<BookingCreatedEvent> {
	
	//@Autowired
	//private CreateChipBookingOutputPort outputPort;

	@Override
	public void handle(BookingCreatedEvent event) {
		// TODO: testar subscribe()
		//outputPort.execute(event.getData()).block();
		
		try {
			// efetuar requisição http ao conector
		} catch (WebClientException ex) {
			// postar evento de erro no kafka event bus
		}
		
	}

}
