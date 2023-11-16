package be.ww.configuration;

import org.axonframework.common.jdbc.ConnectionProvider;
import org.axonframework.common.jdbc.PersistenceExceptionResolver;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.jdbc.EventSchema;
import org.axonframework.eventsourcing.eventstore.jdbc.EventTableFactory;
import org.axonframework.eventsourcing.eventstore.jdbc.JdbcEventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jdbc.MySqlEventTableFactory;
import org.axonframework.serialization.Serializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// see org.axonframework.springboot.autoconfig.JdbcAutoConfiguration
// see org.axonframework.springboot.autoconfig.AxonJobRunrAutoConfiguration
public class AxonConfiguration {

    // The EmbeddedEventStore delegates actual storage and retrieval of events to an EventStorageEngine.
    @Bean
    public EventStore eventStore(
            final EventStorageEngine storageEngine
    ) {
        return EmbeddedEventStore.builder()
                .storageEngine(storageEngine)
                .build();
    }

    @Bean
    public EventSchema eventSchema() {
        return new EventSchema();
    }

    @Bean
    public EventTableFactory eventTableFactory() {
        return MySqlEventTableFactory.INSTANCE;
    }

    @Bean
    public EventStorageEngine eventStorageEngine(
            final Serializer defaultSerializer,
            final PersistenceExceptionResolver persistenceExceptionResolver,
            final @Qualifier("eventSerializer") Serializer eventSerializer,
            final org.axonframework.config.Configuration configuration,
            final ConnectionProvider connectionProvider,
            final TransactionManager transactionManager,
            final EventSchema eventSchema,
            final EventTableFactory tableFactory
    ) {
        final JdbcEventStorageEngine storageEngine = JdbcEventStorageEngine.builder()
                .snapshotSerializer(defaultSerializer)
                .upcasterChain(configuration.upcasterChain())
                .persistenceExceptionResolver(persistenceExceptionResolver)
                .eventSerializer(eventSerializer)
                .snapshotFilter(configuration.snapshotFilter())
                .connectionProvider(connectionProvider)
                .transactionManager(transactionManager)
                .schema(eventSchema)
                .build();

        // If the schema has not been constructed yet, the createSchema method can be used:
        storageEngine.createSchema(tableFactory);

        return storageEngine;
    }

}
