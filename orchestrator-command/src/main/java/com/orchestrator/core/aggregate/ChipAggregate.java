package com.orchestrator.core.aggregate;

import java.util.UUID;

import com.orchestrator.core.command.CreateBookingChipCommand;
import com.orchestrator.core.event.BookingChipCreatedEvent;
import com.orchestrator.core.exception.BusinessException;
import com.orchestrator.core.model.Chip;
import com.orchestrator.infra.annotation.Aggregate;
import com.orchestrator.infra.event.Event;
import com.orchestrator.infra.event.EventBus;

import lombok.RequiredArgsConstructor;

@Aggregate
@RequiredArgsConstructor
public class ChipAggregate {
	
	private final EventBus eventBus;
	
	public void bookingChipCreated(CreateBookingChipCommand command) {
		
		// enriquecer dados, acessar repositórios
		Chip chip = Chip.builder().id(UUID.randomUUID()).name(command.getName())
				.lastName(command.getLastName()).age(command.getAge()).build();
				
		// aplicar regras de negócio e disparar evento		
		try {
			
			if (command.getAge() < 18) {
				
				throw new BusinessException("Não pode ser menor de idade");
				
			}
			
			var event = new BookingChipCreatedEvent(chip.getId().toString(), chip);
			
			eventBus.handle(event);
			
		} catch (BusinessException ex) {
						
			eventBus.publishError(BookingChipCreatedEvent.class, chip.getId().toString(), chip, ex);
			
		}			
				
	}
	
	public void bookingChipErrorWasThrown(Event<?> event,Throwable throwable) {		
		eventBus.publishError(event.getClass(), event.getKey(), event.getData(), throwable);		
	}

}
