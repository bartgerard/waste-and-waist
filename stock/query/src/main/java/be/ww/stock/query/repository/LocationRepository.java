package be.ww.stock.query.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends ElasticsearchRepository<LocationDocument, String> {

    List<LocationDocument> findByHouseHoldId(String houseHoldId);

    Optional<LocationDocument> findByLocationId(String locationId);

    Optional<LocationDocument> findByProductId(String productId);

    Optional<LocationDocument> findByProvisionId(String provisionId);
}
