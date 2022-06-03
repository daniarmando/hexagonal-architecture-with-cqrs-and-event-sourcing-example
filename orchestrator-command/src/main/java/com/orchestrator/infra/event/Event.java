package com.orchestrator.infra.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
public abstract class Event<T> {
	
	protected final String key;
	protected final T data;

}
