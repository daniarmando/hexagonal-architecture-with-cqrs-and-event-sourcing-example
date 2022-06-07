package com.orchestrator.presentation.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.orchestrator.core.exception.BusinessException;
import com.orchestrator.presentation.exception.dto.ErrorMessageDTO;
import com.orchestrator.presentation.exception.dto.ResponseDetailDTO;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class CustomRestExceptionHandler {	
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageDTO> handlerGenericException (Exception ex) {
        log.error("Generic Error - " + ex.getMessage(), ex);
        return buildResponseEntity("internal_error", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(BusinessException.class)    
    public ResponseEntity<ErrorMessageDTO> handleBusinessException(BusinessException ex) {
        log.error("BusinessException: " + ex.getMessage(), ex);  
        String message = ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage();
        return buildResponseEntity("business_violation", message, HttpStatus.BAD_REQUEST);                
    } 
    
    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<ErrorMessageDTO> handlerUnsupportedOperationException(UnsupportedOperationException ex) {
    	log.error("UnsupportedOperationException - " + ex.getMessage(), ex);
        return buildResponseEntity("unsupported_operation", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ErrorMessageDTO> handlerWebExchangeBindException(WebExchangeBindException ex) {
    	log.error("WebExchangeBindException - " + ex.getMessage(), ex);
        return buildResponseEntity(ex.getBindingResult());
    }       

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageDTO> handlerMehodArgumentException(MethodArgumentNotValidException ex) {
    	log.error("MethodArgumentNotValidException - " + ex.getMessage(), ex);
        return buildResponseEntity(ex.getBindingResult());
    }
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<ErrorMessageDTO> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
		log.error("HttpMessageNotReadableException: " + ex.getMessage(), ex);	
		return buildResponseEntity("data_not_readable", ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);		
	}	
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorMessageDTO> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
		log.error("MethodArgumentTypeMismatchException: " + ex.getMessage(), ex);	
		
		var property = "";
		
		if (ex.getCause() instanceof ConversionFailedException) {
			var value = ((ConversionFailedException) ex.getCause()).getValue();
			if (value != null) {
				property = ", " + value.toString() + " value is not acceptable";
			}	
		}
		
		var message = ex.getName() + " parameter was entered in an invalid type" + property;
		return buildResponseEntity("parameter_type_mismatch", message, HttpStatus.BAD_REQUEST);
	}
    
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorMessageDTO> handlerBindException(BindException ex) {
    	log.error("BindException: " + ex.getMessage(), ex);
    	
    	List<ResponseDetailDTO> responseDetailDTO = ex.getBindingResult().getFieldErrors()
    			.stream().map(error -> new ResponseDetailDTO(error.getField(), error.getDefaultMessage()))
    			.collect(Collectors.toList());
 
        return buildResponseWithData(responseDetailDTO);
    }
    
    private ResponseEntity<ErrorMessageDTO> buildResponseEntity(BindingResult bindingResult) {
        var erros = bindingResult.
                getFieldErrors().
                stream().
                map(error -> new ResponseDetailDTO(error.getField(), error.getDefaultMessage())).
                collect(Collectors.toList());
        return buildResponseWithData(erros);
    }

    private ResponseEntity<ErrorMessageDTO> buildResponseWithData(List<ResponseDetailDTO> errorMsg) {
        return new ResponseEntity<>(new ErrorMessageDTO("invalid_fields", "invalid_fields", errorMsg), HttpStatus.BAD_REQUEST);
    }
    
    private ResponseEntity<ErrorMessageDTO> buildResponseEntity(String type, String message, HttpStatus status) {
        return new ResponseEntity<>(new ErrorMessageDTO(type, message), status);
    }

}
