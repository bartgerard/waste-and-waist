package be.ww.household.query.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface HouseHoldRepository extends ElasticsearchRepository<HouseHoldDocument, String>, CustomHouseHoldRepository {
    Optional<HouseHoldDocument> findByHouseHoldIdIs(String houseHoldId);
}
