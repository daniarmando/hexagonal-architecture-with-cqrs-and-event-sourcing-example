package com.orchestrator.infra.command;

public class MissingCommandHandlerException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public MissingCommandHandlerException(String msg) {
        super(msg);
    }

}
