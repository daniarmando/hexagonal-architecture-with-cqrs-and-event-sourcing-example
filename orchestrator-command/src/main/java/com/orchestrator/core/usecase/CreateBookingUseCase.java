package com.orchestrator.core.usecase;

import com.orchestrator.core.command.CreateBookingCommand;
import com.orchestrator.core.model.dto.CreateBookingDTO;
import com.orchestrator.core.port.input.CreateBookingInputPort;
import com.orchestrator.infra.annotation.UseCase;
import com.orchestrator.infra.command.CommandBus;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateBookingUseCase implements CreateBookingInputPort {
	
	private final CommandBus commandBus;

	@Override
	public void execute(CreateBookingDTO createChipBookingDTO) {				
		commandBus.handle(new CreateBookingCommand(
				createChipBookingDTO.getProduct(), 
				createChipBookingDTO.getValue(), 
				createChipBookingDTO.getSalesman(),
				createChipBookingDTO.getCustomer()));		
	}

}
