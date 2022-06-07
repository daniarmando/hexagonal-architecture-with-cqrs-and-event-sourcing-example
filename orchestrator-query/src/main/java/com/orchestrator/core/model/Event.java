package com.orchestrator.core.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Event {
	
	@JsonIgnore
	@EqualsAndHashCode.Include
	private UUID id;
	
	private String key;	
	private String name;		
	private Long timestamp;
	private Object data;		
	
}
