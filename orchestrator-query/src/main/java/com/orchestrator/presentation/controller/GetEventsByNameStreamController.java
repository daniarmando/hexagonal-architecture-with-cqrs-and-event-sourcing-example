package com.orchestrator.presentation.controller;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orchestrator.core.model.Event;
import com.orchestrator.core.port.input.GetEventsByNameInputPort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping(GetEventsByNameStreamController.URL)
public class GetEventsByNameStreamController {
	
public static final String URL = "/v1/event/stream";
	
	private final GetEventsByNameInputPort inputPort;
	
	@GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Event> streamFlux() {
		
		Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
		var events = inputPort.execute("BookingCreatedEvent");
		
		return Flux.zip(events, interval, (key, value) -> key);

	}

}
