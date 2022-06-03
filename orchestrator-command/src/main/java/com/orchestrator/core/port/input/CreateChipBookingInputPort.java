package com.orchestrator.core.port.input;

import com.orchestrator.core.model.dto.CreateBookingChipDTO;

public interface CreateChipBookingInputPort {

	void execute(CreateBookingChipDTO createChipBookingDTO);
	
}
