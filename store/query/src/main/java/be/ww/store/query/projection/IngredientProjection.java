package be.ww.store.query.projection;

import be.ww.shared.type.Amount;
import be.ww.shared.type.ProductId;
import be.ww.shared.type.ingredient.IngredientId;
import be.ww.shared.type.ingredient.NutritionalFact;
import be.ww.store.api.event.ProductAddedEvent;
import be.ww.store.api.query.FindIngredientByIdQuery;
import be.ww.store.api.query.Ingredient;
import be.ww.store.api.query.IngredientResponseData;
import be.ww.store.api.query.Product;
import be.ww.store.query.repository.AllergenField;
import be.ww.store.query.repository.IngredientDocument;
import be.ww.store.query.repository.IngredientRepository;
import be.ww.store.query.repository.ProductField;
import be.ww.store.query.repository.QuantityField;
import be.ww.store.query.repository.StoreField;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.stream.Collectors.toUnmodifiableMap;

@Component
@RequiredArgsConstructor
@Slf4j
@ProcessingGroup("ww-store-query-IngredientProjection")
public class IngredientProjection {

    private final IngredientRepository ingredientRepository;

    @EventHandler
    public void on(
            final ProductAddedEvent event
    ) {
        final IngredientDocument ingredientDocument = ingredientRepository.findByIngredientId(event.ingredientId().id())
                .orElseGet(() -> IngredientDocument.builder()
                        .ingredientId(event.ingredientId().id())
                        .build()
                );

        final IngredientDocument newDocument = ingredientDocument.toBuilder()
                .product(ProductField.builder()
                        .productId(event.productId().id())
                        .name(event.productName())
                        .brand(event.brand())
                        .stores(StoreField.from(event.stores()))
                        .unitQuantity(QuantityField.from(event.unitQuantity()))
                        .nutritionalFacts(event.nutritionalFacts()
                                .entrySet()
                                .stream()
                                .collect(toUnmodifiableMap(
                                        Map.Entry::getKey,
                                        fact -> fact.getValue().value()
                                ))
                        )
                        .allergens(AllergenField.from(event.allergens()))
                        .build()
                )
                //.nutritionalFactRanges(ingredientDocument.getNutritionalFactRanges())
                .build();

        ingredientRepository.save(newDocument);
    }

    @QueryHandler
    public IngredientResponseData handle(
            final FindIngredientByIdQuery query
    ) {
        return ingredientRepository.findByIngredientId(query.ingredientId().id())
                .map(ingredient -> new IngredientResponseData(Ingredient.builder()
                        .ingredientId(IngredientId.of(ingredient.getIngredientId()))
                        .products(ingredient.getProducts()
                                .stream()
                                .map(product -> Product.builder()
                                        .productId(ProductId.of(product.productId()))
                                        .productName(product.name())
                                        .brand(product.brand())
                                        .stores(StoreField.toStores(product.stores()))
                                        .unitQuantity(product.unitQuantity().toQuantity())
                                        .nutritionalFacts(product.nutritionalFacts()
                                                .entrySet()
                                                .stream()
                                                .collect(toUnmodifiableMap(
                                                        Map.Entry::getKey,
                                                        fact -> Amount.of(fact.getValue())
                                                ))
                                        )
                                        .allergens(AllergenField.toAllergens(product.allergens()))
                                        .build()
                                )
                                .toList()
                        )
                        .build()
                ))
                .orElseThrow(() -> new IllegalArgumentException("ingredientId not found"));
    }

}
