package com.orchestrator.core.command;

import com.orchestrator.infra.command.Command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class CreateBookingChipCommand extends Command {
	
	private String name;
	private String lastName;
	private Integer age;

}
