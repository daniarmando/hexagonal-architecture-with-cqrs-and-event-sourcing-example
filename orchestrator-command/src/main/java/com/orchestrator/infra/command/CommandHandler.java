package com.orchestrator.infra.command;

public interface CommandHandler<T extends Command> {
	
	void handle(T command);

}
