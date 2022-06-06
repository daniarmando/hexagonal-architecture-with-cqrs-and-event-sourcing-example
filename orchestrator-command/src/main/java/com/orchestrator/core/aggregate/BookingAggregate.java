package com.orchestrator.core.aggregate;

import java.util.UUID;

import com.orchestrator.core.command.CreateBookingCommand;
import com.orchestrator.core.event.BookingCreatedEvent;
import com.orchestrator.core.exception.BusinessException;
import com.orchestrator.core.model.Booking;
import com.orchestrator.infra.annotation.Aggregate;
import com.orchestrator.infra.event.Event;
import com.orchestrator.infra.event.EventBus;

import lombok.RequiredArgsConstructor;

@Aggregate
@RequiredArgsConstructor
public class BookingAggregate {
	
	private final EventBus eventBus;
	
	public void bookingCreated(CreateBookingCommand command) {
		
		// enriquecer dados, acessar repositórios
		Booking booking = Booking.builder().id(UUID.randomUUID()).product(command.getProduct())
				.value(command.getValue()).salesman(command.getSalesman())
				.customer(command.getCustomer()).build();
				
		// aplicar regras de negócio e disparar evento		
		try {
			
			if (command.getValue().intValue() < 0) {
				
				throw new BusinessException("Não pode ser menor que zero");
				
			}
			
			var event = new BookingCreatedEvent(booking.getId().toString(), booking);
			
			eventBus.handle(event);
			
		} catch (BusinessException ex) {
						
			eventBus.publishError(BookingCreatedEvent.class, booking.getId().toString(), booking, ex);
			
		}			
				
	}
	
	public void bookingChipErrorWasThrown(Event<?> event,Throwable throwable) {		
		eventBus.publishError(event.getClass(), event.getKey(), event.getData(), throwable);		
	}

}
