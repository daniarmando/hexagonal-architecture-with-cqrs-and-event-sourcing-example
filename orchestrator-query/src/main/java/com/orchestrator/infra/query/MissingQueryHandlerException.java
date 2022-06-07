package com.orchestrator.infra.query;

public class MissingQueryHandlerException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public MissingQueryHandlerException(String msg) {
        super(msg);
    }

}
