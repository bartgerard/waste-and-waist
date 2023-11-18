package be.ww.stock.query.repository;

import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LocationRepository extends ElasticsearchRepository<LocationDocument, String> {

	Optional<LocationDocument> findByLocationId(String locationId);

	Optional<LocationDocument> findByProductId(String productId);

	Optional<LocationDocument> findByProvisionId(String provisionId);
}
