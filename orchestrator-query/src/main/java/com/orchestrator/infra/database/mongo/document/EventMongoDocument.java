package com.orchestrator.infra.database.mongo.document;

import java.util.UUID;

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
		
	@EqualsAndHashCode.Include
	private UUID id;
		
	private String key;		
	private String name;		
	private Long timestamp;	
	private Object data;
	
	public Event toDomain() {
		return Event.builder()
				.id(id).key(key).name(name).timestamp(timestamp)
				.data(data).build();
	}

}