package be.ww.configuration.test;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TestRepository extends ElasticsearchRepository<TestDocument, String> {
}
