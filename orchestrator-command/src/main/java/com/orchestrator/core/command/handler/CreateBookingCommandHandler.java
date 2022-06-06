package com.orchestrator.core.command.handler;

import com.orchestrator.core.aggregate.BookingAggregate;
import com.orchestrator.core.command.CreateBookingCommand;
import com.orchestrator.infra.annotation.Handler;
import com.orchestrator.infra.command.CommandHandler;

import lombok.RequiredArgsConstructor;

@Handler
@RequiredArgsConstructor
public class CreateBookingCommandHandler implements CommandHandler<CreateBookingCommand> {
	
	private final BookingAggregate aggregate;
	
	@Override
	public void handle(CreateBookingCommand command) {
 		aggregate.bookingCreated(command);
	}

}
