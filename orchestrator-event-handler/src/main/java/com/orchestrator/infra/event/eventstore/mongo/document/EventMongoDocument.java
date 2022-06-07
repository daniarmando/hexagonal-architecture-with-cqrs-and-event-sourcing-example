package com.orchestrator.infra.event.eventstore.mongo.document;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.orchestrator.core.model.Event;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document("events")
public class EventMongoDocument {
	
	@Id
	@EqualsAndHashCode.Include
	private UUID id;
	
	@Indexed
	private String key;
	
	@Indexed
	private String name;
		
	private Long timestamp;	
	private Object data;
	
	public static EventMongoDocument fromDomain(Event event) {
		return EventMongoDocument.builder()
				.id(event.getId()).key(event.getKey())
				.name(event.getName()).timestamp(event.getTimestamp())
				.data(event.getData()).build();
	}

}
