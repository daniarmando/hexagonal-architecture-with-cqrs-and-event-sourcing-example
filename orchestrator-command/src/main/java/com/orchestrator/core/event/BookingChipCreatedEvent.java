package com.orchestrator.core.event;

import com.orchestrator.core.model.Chip;
import com.orchestrator.infra.event.Event;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class BookingChipCreatedEvent extends Event<Chip> {	
	
	public BookingChipCreatedEvent(String key, Chip data) {
		super(key, data);
	}

}
