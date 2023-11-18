package be.ww.stock.command.repository;

import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface StockProductRepository extends ElasticsearchRepository<StockProductDocument, String> {


	Optional<StockProductDocument> findByProductId(String productId);


}
