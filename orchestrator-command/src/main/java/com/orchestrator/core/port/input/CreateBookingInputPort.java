package com.orchestrator.core.port.input;

import com.orchestrator.core.model.dto.CreateBookingDTO;

public interface CreateBookingInputPort {

	void execute(CreateBookingDTO createChipBookingDTO);
	
}
