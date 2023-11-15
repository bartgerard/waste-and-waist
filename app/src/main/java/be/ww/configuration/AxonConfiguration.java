package be.ww.configuration;

import org.axonframework.common.jdbc.ConnectionProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.jdbc.EventTableFactory;
import org.axonframework.eventsourcing.eventstore.jdbc.JdbcEventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jdbc.MySqlEventTableFactory;
import org.axonframework.serialization.Serializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class AxonConfiguration {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource datasource() {
        return DataSourceBuilder.create().build();
    }

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
    public EventTableFactory eventTableFactory() {
        return MySqlEventTableFactory.INSTANCE;
    }

    // The JdbcEventStorageEngine stores events in a JDBC-compatible data source.
    @Bean
    public EventStorageEngine storageEngine(
            final Serializer serializer,
            final ConnectionProvider connectionProvider,
            final @Qualifier("eventSerializer") Serializer eventSerializer,
            final TransactionManager transactionManager,
            final EventTableFactory tableFactory
    ) {
        final JdbcEventStorageEngine storageEngine = JdbcEventStorageEngine.builder()
                .snapshotSerializer(serializer)
                .connectionProvider(connectionProvider)
                .eventSerializer(eventSerializer)
                .transactionManager(transactionManager)
                // ...
                .build();

        // If the schema has not been constructed yet, the createSchema method can be used:
        storageEngine.createSchema(tableFactory);

        return storageEngine;
    }

    /*
    @Bean
    public SagaStore<?> sagaStore(
            final DataSource dataSource
    ) throws SQLException {
        final JdbcSagaStore sagaStore = JdbcSagaStore.builder()
                .dataSource(dataSource)
                .build();
        sagaStore.createSchema();
        return sagaStore;
    }
     */

    /*
    @Bean
    public DeadlineManager deadlineManager(
            @Qualifier("eventSerializer") final Serializer serializer,
            final JobScheduler jobScheduler,
            final ScopeAwareProvider scopeAwareProvider,
            final TransactionManager transactionManager,
            final SpanFactory spanfactory
    ) {
        return JobRunrDeadlineManager.builder()
                .jobScheduler(jobScheduler)
                .scopeAwareProvider(scopeAwareProvider)
                .serializer(serializer)
                .transactionManager(transactionManager)
                .spanFactory(spanfactory)
                .build();
    }

     */

}
