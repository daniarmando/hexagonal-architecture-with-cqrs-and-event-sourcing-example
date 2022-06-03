package com.orchestrator.core.usecase;

import com.orchestrator.core.command.CreateBookingChipCommand;
import com.orchestrator.core.model.dto.CreateBookingChipDTO;
import com.orchestrator.core.port.input.CreateChipBookingInputPort;
import com.orchestrator.infra.annotation.UseCase;
import com.orchestrator.infra.command.CommandBus;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateChipBookingUseCase implements CreateChipBookingInputPort {
	
	private final CommandBus commandBus;

	@Override
	public void execute(CreateBookingChipDTO createChipBookingDTO) {				
		commandBus.handle(new CreateBookingChipCommand(createChipBookingDTO.getName(), createChipBookingDTO.getLastName(), createChipBookingDTO.getAge()));		
	}

}
