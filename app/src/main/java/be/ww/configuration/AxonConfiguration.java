package be.ww.configuration;

import org.axonframework.common.jdbc.ConnectionProvider;
import org.axonframework.common.jdbc.PersistenceExceptionResolver;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventhandling.tokenstore.jdbc.GenericTokenTableFactory;
import org.axonframework.eventhandling.tokenstore.jdbc.JdbcTokenStore;
import org.axonframework.eventhandling.tokenstore.jdbc.TokenSchema;
import org.axonframework.eventhandling.tokenstore.jdbc.TokenTableFactory;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.jdbc.EventSchema;
import org.axonframework.eventsourcing.eventstore.jdbc.EventTableFactory;
import org.axonframework.eventsourcing.eventstore.jdbc.JdbcEventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jdbc.MySqlEventTableFactory;
import org.axonframework.modelling.saga.repository.jdbc.GenericSagaSqlSchema;
import org.axonframework.modelling.saga.repository.jdbc.JdbcSagaStore;
import org.axonframework.modelling.saga.repository.jdbc.SagaSqlSchema;
import org.axonframework.serialization.Serializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

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
    public TokenSchema tokenSchema() {
        return new TokenSchema();
    }

    @Bean
    public TokenTableFactory tokenTableFactory() {
        return GenericTokenTableFactory.INSTANCE;
    }

    @Bean
    public TokenStore tokenStore(
            final ConnectionProvider connectionProvider,
            final Serializer serializer,
            final TokenSchema tokenSchema,
            final TokenTableFactory tokenTableFactory
    ) {
        final JdbcTokenStore store = JdbcTokenStore.builder()
                .connectionProvider(connectionProvider)
                .schema(tokenSchema)
                .serializer(serializer)
                .build();
        store.createSchema(tokenTableFactory);
        return store;
    }

    @Bean
    public SagaSqlSchema sagaSqlSchema() {
        return new GenericSagaSqlSchema();
    }

    @Bean
    public JdbcSagaStore sagaStoreWithSchema(
            ConnectionProvider connectionProvider,
            Serializer serializer,
            SagaSqlSchema schema
    ) throws SQLException {
        final JdbcSagaStore store = JdbcSagaStore.builder()
                .connectionProvider(connectionProvider)
                .sqlSchema(schema)
                .serializer(serializer)
                .build();
        // store.createSchema(); // TODO
        return store;
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
            final EventTableFactory eventTableFactory
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
        storageEngine.createSchema(eventTableFactory);

        return storageEngine;
    }

}
