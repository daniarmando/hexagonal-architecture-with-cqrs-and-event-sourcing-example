package com.orchestrator.core.model;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Booking {
	
    private UUID id;
	private String product; 
	private BigDecimal value;
	private String salesman;
	private String customer;

}
