package com.orchestrator.core.util;

import java.util.function.Supplier;

public final class ObjectUtils {
	
	private ObjectUtils() {}
	
	public static <T> T safeEval(Supplier<T> supplier) {
		
		try {
			return supplier.get();
		} catch (NullPointerException e) {
			return null;
		}
		
	}
	
	public static <T> boolean nonNullSafeEval(Supplier<T> supplier) {
		try {
			return supplier.get() != null;
		} catch (NullPointerException e) {
			return false;
		}
	}

}
