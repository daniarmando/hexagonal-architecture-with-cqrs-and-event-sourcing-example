package com.orchestrator.infra.query;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@SuppressWarnings({ "unchecked", "rawtypes" })
public class QueryBusImpl implements QueryBus {
	
	private final Map<Class, QueryHandler> handlers;
	
	public QueryBusImpl(List<QueryHandler> queryHandlerImplementations) {
		
		log.info("Mapping query handlers");
		
		this.handlers = new HashMap<>();
		queryHandlerImplementations.forEach(queryHandler -> {
			Class<?> queryClass = getQueryClass(queryHandler);
			handlers.put(queryClass, queryHandler);
		});			
	}

	@Override
	public <T> T handle(Query<T> query) {
		
		log.info("Handling {} {}", query.getClass().getSimpleName(), query);
		
		if (!handlers.containsKey(query.getClass())) {
			throw new MissingQueryHandlerException(String.format("No handler for %s or handler was not annotated with @Handler", query.getClass().getSimpleName()));
		}
		
		var queryResult = (T) handlers.get(query.getClass()).handle(query); 
		
		log.info("Handling of the query {} done", query.getClass().getSimpleName());
		
		return queryResult;
	}
	
	private Class<?> getQueryClass(QueryHandler handler) {
		Type commandInterface = ((ParameterizedType) handler.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[1];
		return getClass(commandInterface.getTypeName());
	}
	
	private Class<?> getClass(String name) {
		try {
			return Class.forName(name);
		} catch (Exception ex) {
			log.error("Error -> ", ex);
			return null;
		}
	}

}
