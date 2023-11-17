package be.ww.stock.command.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface StockHouseHoldRepository extends ElasticsearchRepository<StockHouseHoldDocument, String> {
    Optional<StockHouseHoldDocument> findByHouseHoldIdIs(String houseHoldId);
}
