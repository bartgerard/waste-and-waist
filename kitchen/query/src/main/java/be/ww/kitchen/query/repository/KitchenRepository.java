package be.ww.kitchen.query.repository;

import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface KitchenRepository extends ElasticsearchRepository<KitchenDocument, String> {

	Optional<KitchenDocument> findByRecipeId(String recipeId);
}
