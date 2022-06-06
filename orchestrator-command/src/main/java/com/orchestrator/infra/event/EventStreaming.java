package com.orchestrator.infra.event;

import java.sql.Timestamp;
import java.time.Instant;

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
	private Long timestamp;
	private Object data;
	
	public EventStreaming(String key, String name, Object data) {
		this.key = key;
		this.name = name;
		this.timestamp = Timestamp.from(Instant.now()).getTime();
		this.data = data;	
	}

}
