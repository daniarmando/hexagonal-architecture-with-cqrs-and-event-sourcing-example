package com.orchestrator.infra.event;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.orchestrator.core.port.output.PublishToEventStreamOutputPort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@SuppressWarnings({ "unchecked", "rawtypes" })
public class EventBusImpl implements EventBus {
    
	private final Map<Class, EventHandler> handlers;	
	private final PublishToEventStreamOutputPort eventStream;
	
	public EventBusImpl(List<EventHandler<?>> eventHandlerImplementations, PublishToEventStreamOutputPort eventStream) {
		
		log.info("Mapping event handlers");		
		
		this.handlers = new HashMap<>();
		eventHandlerImplementations.forEach(eventHandler -> {
			Class<?> eventClass = getEventClass(eventHandler);
			handlers.put(eventClass, eventHandler);				
		});
				
		this.eventStream = eventStream;			
	}
	
	@Override
	public void handle(Event<?> event) {
		
		var eventName = event.getClass().getSimpleName();
		
		log.info("Handling {} {}", eventName, event);
		
		var handler = handlers.get(event.getClass());					
		
		try {
			publishEvent(event, handler);
			log.info("Handling of the event {} done", event.getClass().getSimpleName());
		} catch (Exception ex) {			
			publishError(event.getClass(), event.getKey(), event.getData(), ex);
			log.error("Handling of the event error", ex);
		}
						
	}
	
	@Override
	public void publishError(Event<?> event, Throwable throwable) {		
		publishError(event.getClass(), event.getKey(), event.getData(), throwable);						
	}
	
	@Override
	public void publishError(Class<?> eventType, String key, Object data, Throwable throwable) {
		
		var eventCauserName = eventType.getSimpleName();		
		var causer = new ErrorEventCauser(eventCauserName, data, throwable);				
		var eventStreaming = new EventStreaming(key, "ErrorEvent", causer);		
		
		eventStream.error(eventStreaming);
	}
	
	
	private Class<?> getEventClass(EventHandler<?> handler) {		
		Type eventInterface = ((ParameterizedType) handler.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
		return getClass(eventInterface.getTypeName());
	}
	
	private Class<?> getClass(String name) {
		try {
			return Class.forName(name);
		} catch (Exception ex) {
			log.error("Error -> ", ex);
			return null;
		}
	}
	
	private void publishEvent(Event event, EventHandler eventHandler) {			
				
		var eventName = event.getClass().getSimpleName();
		var eventStreaming = new EventStreaming(event.getKey(), eventName, event.getData());		
		
		eventStream.send(eventStreaming)
			.addCallback(new ListenableFutureCallback<SendResult<String, EventStreaming>>() {

				@Override
				public void onSuccess(SendResult<String, EventStreaming> result) {		
					if (Objects.isNull(eventHandler)) {
						log.warn("No handlers to fire after {} has been published to event store or handler not annotated as a spring bean", event.getClass().getSimpleName());						
					} else {
						eventHandler.handle(event);
					}
				}
	
				@Override
				public void onFailure(Throwable ex) {	
					log.error("Error handling event in event store", ex);									
				}
				
			});	
	}
	
}
