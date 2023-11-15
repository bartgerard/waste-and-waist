package be.ww.household.adapter.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface HouseHoldRepository extends ElasticsearchRepository<HouseHoldDocument, String> {
    Optional<HouseHoldDocument> findByHouseHoldIdIs(String houseHoldId);
}
