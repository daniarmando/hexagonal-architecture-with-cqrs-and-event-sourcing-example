package com.orchestrator.infra.event.eventstore.mongo;

import org.bson.UuidRepresentation;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClientSettings;

@Configuration
public class MongoConfig {
	
	@Bean
    public MongoClientSettingsBuilderCustomizer mongoClientSettingsBuilderCustomizer() {
        return clientSettingsBuilder -> {
            CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                    CodecRegistries.fromProviders(PojoCodecProvider.builder().build()),
                    MongoClientSettings.getDefaultCodecRegistry());
            clientSettingsBuilder
                    .codecRegistry(codecRegistry)
                    .uuidRepresentation(UuidRepresentation.STANDARD);
        };
    }

}
