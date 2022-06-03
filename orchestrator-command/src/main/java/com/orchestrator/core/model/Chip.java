package com.orchestrator.core.model;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Chip {
	
    private UUID id;	
	private String name;
	private String lastName;
	private Integer age;    

}
