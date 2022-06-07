package com.orchestrator.presentation.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orchestrator.core.model.Event;
import com.orchestrator.core.port.input.GetEventsByNameInputPort;
import com.orchestrator.presentation.util.HttpStatusMessageUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping(GetEventsByNameController.URL)
public class GetEventsByNameController {
	
public static final String URL = "/v1/event";
	
	private final GetEventsByNameInputPort inputPort;
		
	@Operation(summary = "Query to get events by name")	
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = HttpStatusMessageUtils.OK),
			@ApiResponse(responseCode = "400", description = HttpStatusMessageUtils.BAD_REQUEST),
			@ApiResponse(responseCode = "401", description = HttpStatusMessageUtils.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", description = HttpStatusMessageUtils.FORBIDDEN) })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)	
	public Flux<Event> execute(@RequestParam final String name) {		
	 	return inputPort.execute(name);
	}

}
