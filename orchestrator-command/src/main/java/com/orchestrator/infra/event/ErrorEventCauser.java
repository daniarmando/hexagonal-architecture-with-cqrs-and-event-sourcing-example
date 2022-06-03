package com.orchestrator.infra.event;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorEventCauser {
	
	private String actionNameThatCausedTheError;
	private Object actionDataThatCausedTheError;
	private String nameOfTheExceptionThatWasThrown;
	private String messageOfTheExceptionThatWasThrown;
	
	public ErrorEventCauser(String actionNameThatCausedTheError, Object actionDataThatCausedTheError, Throwable throwable) {
		this.actionNameThatCausedTheError = actionNameThatCausedTheError;
		this.actionDataThatCausedTheError = actionDataThatCausedTheError;
		this.nameOfTheExceptionThatWasThrown = throwable.getClass().getSimpleName();
		this.messageOfTheExceptionThatWasThrown = throwable.getMessage();
	}
	
}
