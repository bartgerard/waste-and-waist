package be.ww.store.query.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface IngredientRepository extends ElasticsearchRepository<IngredientDocument, String> {
    Optional<IngredientDocument> findByIngredientId(String ingredientId);
}
