package com.orchestrator.core.event.handler;

import org.springframework.web.reactive.function.client.WebClientException;

import com.orchestrator.core.event.BookingChipCreatedEvent;
import com.orchestrator.infra.annotation.Handler;
import com.orchestrator.infra.event.EventHandler;

@Handler
public class BookingChipCreatedEventHandler implements EventHandler<BookingChipCreatedEvent> {
	
	//@Autowired
	//private CreateChipBookingOutputPort outputPort;

	@Override
	public void handle(BookingChipCreatedEvent event) {
		// TODO: testar subscribe()
		//outputPort.execute(event.getData()).block();
		
		try {
			// efetuar requisição http ao conector
		} catch (WebClientException ex) {
			// postar evento de erro no kafka event bus
		}
		
	}

}
