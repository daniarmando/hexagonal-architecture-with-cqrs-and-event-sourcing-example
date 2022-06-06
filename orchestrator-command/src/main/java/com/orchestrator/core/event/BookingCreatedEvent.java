package com.orchestrator.core.event;

import com.orchestrator.core.model.Booking;
import com.orchestrator.infra.event.Event;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class BookingCreatedEvent extends Event<Booking> {	
	
	public BookingCreatedEvent(String key, Booking data) {
		super(key, data);
	}

}
