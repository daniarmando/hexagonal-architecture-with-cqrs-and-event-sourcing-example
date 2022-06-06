package com.orchestrator.core.command;

import java.math.BigDecimal;

import com.orchestrator.infra.command.Command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class CreateBookingCommand extends Command {
	
	private String product; 
	private BigDecimal value;
	private String salesman;
	private String customer;

}
