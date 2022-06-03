package com.orchestrator.core.util;

import com.orchestrator.core.exception.BusinessException;

public final class BusinessAssertionUtils {
	
	private BusinessAssertionUtils() {}
	
	public static void argumentNotNull(Object obj, String message) {	  
	   try{
		   
	      if (obj == null) {
	    	  throw new IllegalArgumentException(message);
	      }	         
	 
	   } catch(IllegalArgumentException e){
	      throw new BusinessException(e);	
	   }
	}
	
	public static void argumentNotBlank(String value, String message) {	  
		   try{
		      if (value == null || value.isBlank()) {
		    	  throw new IllegalArgumentException(message);
		      }	         
		 
		   } catch(IllegalArgumentException e){
		      throw new BusinessException(e);	
		   }
		}
	
	public static void stateTrue(boolean condition, String message) {		
	   if (!condition) {
		   throw new BusinessException(message);   
	   }	     			  
	}

	public static void stateFalse(boolean condition, String message) {		
		stateTrue(!condition, message);  
	}

}
