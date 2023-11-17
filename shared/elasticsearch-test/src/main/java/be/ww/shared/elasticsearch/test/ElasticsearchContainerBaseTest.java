package be.ww.shared.elasticsearch.test;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@Tag("elasticsearch")
@Tag("slow")
public abstract class ElasticsearchContainerBaseTest {
    private static final String ELASTICSEARCH_IMAGE = "docker.elastic.co/elasticsearch/elasticsearch:7.17.15";
    @Container
    protected static final ElasticsearchContainer ELASTICSEARCH_CONTAINER = new ElasticsearchContainer(ELASTICSEARCH_IMAGE);

    public ElasticsearchContainerBaseTest() {
        // no-op
    }

    @DynamicPropertySource
    static void elasticProperties(
            final DynamicPropertyRegistry registry
    ) {
        registry.add("spring.elasticsearch.uris", ELASTICSEARCH_CONTAINER::getHttpHostAddress);
    }

    @Test
    void isContainerRunning() {
        assertTrue(ELASTICSEARCH_CONTAINER.isRunning());
    }
}
