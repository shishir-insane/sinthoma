package com.sk.sinthoma.core.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.data.mongodb.core.index.IndexResolver;
import org.springframework.data.mongodb.core.index.MongoPersistentEntityIndexResolver;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.stereotype.Component;

import com.sk.sinthoma.core.auth.model.User;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MongoDBIndexConfig {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoMappingContext mongoMappingContext;

    @EventListener(ApplicationReadyEvent.class)
    public void initIndicesAfterStartup() {
	IndexOperations indexOps = mongoTemplate.indexOps(User.class);
	IndexResolver resolver = new MongoPersistentEntityIndexResolver(mongoMappingContext);
	resolver.resolveIndexFor(User.class).forEach(indexOps::ensureIndex);
	log.info("MongoDB Indices ensured.");
    }
}
