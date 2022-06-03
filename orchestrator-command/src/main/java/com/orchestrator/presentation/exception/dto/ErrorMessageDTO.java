package com.orchestrator.presentation.exception.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorMessageDTO {
    private String type;
    private String message;
    private List<ResponseDetailDTO> data;

    public ErrorMessageDTO(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public ErrorMessageDTO(String type, String message, List<ResponseDetailDTO> data) {
        this.type = type;
        this.message = message;
        this.data = data;
    }

}
