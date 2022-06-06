package com.orchestrator.presentation.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.orchestrator.core.model.dto.CreateBookingDTO;
import com.orchestrator.core.port.input.CreateBookingInputPort;
import com.orchestrator.presentation.util.HttpStatusMessageUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(CreateBookingController.URL)
public class CreateBookingController {
	
public static final String URL = "/v1/booking";
	
	private final CreateBookingInputPort inputPort;
		
	@Operation(summary = "Command to make a booking")	
	@ApiResponses(value = { @ApiResponse(responseCode = "202", description = HttpStatusMessageUtils.ACCEPTED),
			@ApiResponse(responseCode = "400", description = HttpStatusMessageUtils.BAD_REQUEST),
			@ApiResponse(responseCode = "401", description = HttpStatusMessageUtils.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", description = HttpStatusMessageUtils.FORBIDDEN),
            @ApiResponse(responseCode = "404", description = HttpStatusMessageUtils.NOT_FOUND) })
	@PostMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void execute(@RequestBody @Valid CreateBookingDTO createBookingChipDTO) {		
		inputPort.execute(createBookingChipDTO);		
	}

}
