package com.orchestrator.core.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingChipDTO {	
	
	@NotNull
	private String name;
	
	@NotNull
	private String lastName;
	
	@Min(1)
	@NotNull	
	private Integer age; 

}
