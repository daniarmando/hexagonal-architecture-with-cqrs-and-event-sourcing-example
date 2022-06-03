package com.orchestrator.infra.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(Include.NON_NULL)
public class EventStreaming{
	
	@EqualsAndHashCode.Include
	private String key;
	
	private String name;		
	private Object data;
	
	public EventStreaming(String key, String name, Object data) {
		this.key = key;
		this.name = name;
		this.data = data;	
	}

}
