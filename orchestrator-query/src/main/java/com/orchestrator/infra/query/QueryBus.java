package com.orchestrator.infra.query;

public interface QueryBus {
	
	<T> T handle(Query<T> query);

}
