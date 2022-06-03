package com.orchestrator.infra.event.kafka;

public class MissingKafkaTopicException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public MissingKafkaTopicException(String msg) {
        super(msg);
    }

}
