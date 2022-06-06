package com.orchestrator.core.model.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingDTO {
	
	@NotEmpty
	@NotBlank
	private String product;
	
	@Min(1)
	@NotNull
	private BigDecimal value;
		
	private String salesman;
		
	@NotEmpty
	@NotBlank	
	private String customer;		

}
