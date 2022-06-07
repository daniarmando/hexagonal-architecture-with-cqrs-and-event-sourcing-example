package com.orchestrator.infra.query;

public interface QueryHandler<T, U extends Query<T>> {
	
	T handle(U query);

}
