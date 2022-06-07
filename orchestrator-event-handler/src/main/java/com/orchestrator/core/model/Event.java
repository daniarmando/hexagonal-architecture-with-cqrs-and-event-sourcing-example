package com.orchestrator.core.model;

import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Event {
	
	@EqualsAndHashCode.Include
	private UUID id;
	
	private String key;	
	private String name;		
	private Long timestamp;
	private Object data;		
	
	public UUID getId() {
		if (Objects.isNull(id)) {
			id = UUID.randomUUID();
		}
		return id;
	}
}
