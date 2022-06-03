package com.orchestrator.core.command.handler;

import com.orchestrator.core.aggregate.ChipAggregate;
import com.orchestrator.core.command.CreateBookingChipCommand;
import com.orchestrator.infra.annotation.Handler;
import com.orchestrator.infra.command.CommandHandler;

import lombok.RequiredArgsConstructor;

@Handler
@RequiredArgsConstructor
public class CreateBookingChipCommandHandler implements CommandHandler<CreateBookingChipCommand> {
	
	private final ChipAggregate aggregate;
	
	@Override
	public void handle(CreateBookingChipCommand command) {
 		aggregate.bookingChipCreated(command);
	}

}
