package com.orchestrator.infra.command;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CommandBusImpl implements CommandBus {
	
	private final Map<Class, CommandHandler> handlers;
	
	public CommandBusImpl(List<CommandHandler<?>> commandHandlerImplementations) {
		
		log.info("Mapping command handlers");
		
		this.handlers = new HashMap<>();
		commandHandlerImplementations.forEach(commandHandler -> {
			Class<?> commandClass = getCommandClass(commandHandler);
			handlers.put(commandClass, commandHandler);
		});			
	}
	
	@Override
	public Mono<Void> handle(Command command) {
		
		CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
	        
			log.info("Handling {} {}", command.getClass().getSimpleName(), command);	
			
			if (!handlers.containsKey(command.getClass())) {
				throw new MissingCommandHandlerException(String.format("No handler for %s or handler was not annotated with @Handler", command.getClass().getSimpleName()));
			}
			
			handlers.get(command.getClass()).handle(command);
			
			log.info("Handling of the command {} done", command.getClass().getSimpleName());
			
			return null;
		});
				
		return Mono.fromFuture(future);
			
	}
	
	private Class<?> getCommandClass(CommandHandler<?> handler) {		
		Type commandInterface = ((ParameterizedType) handler.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
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
