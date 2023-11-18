package be.ww.store.query.projection;

import be.ww.shared.type.ingredient.IngredientId;
import be.ww.store.api.event.ProductAddedEvent;
import be.ww.store.api.query.FindIngredientByIdQuery;
import be.ww.store.api.query.Ingredient;
import be.ww.store.api.query.IngredientResponseData;
import be.ww.store.query.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@ProcessingGroup("ww-store-query-IngredientProjection")
public class IngredientProjection {

    private final IngredientRepository ingredientRepository;
    private final QueryUpdateEmitter queryUpdateEmitter;

    @EventHandler
    public void on(
            final ProductAddedEvent event
    ) {
    }

    @QueryHandler
    public IngredientResponseData handle(
            final FindIngredientByIdQuery query
    ) {
        return ingredientRepository.findByIngredientId(query.ingredientId().id())
                .map(ingredient -> new IngredientResponseData(Ingredient.builder()
                        .ingredientId(IngredientId.of(ingredient.getIngredientId()))
                        // TODO
                        .build()
                ))
                .orElseThrow(() -> new IllegalArgumentException("ingredientId not found"));
    }

}
