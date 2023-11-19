package be.ww.store.web;

import be.ww.shared.type.ProductId;
import be.ww.shared.type.ingredient.IngredientId;
import be.ww.store.api.command.AddProductCommand;
import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static java.util.Objects.requireNonNullElseGet;

@RestController
@RequestMapping(path = "/products")
@RequiredArgsConstructor
public class ProductRestController {

    private final ReactorCommandGateway reactorCommandGateway;

    @PostMapping
    public Mono<Object> register(
            @RequestBody final ProductRequestData productRequestData
    ) {
        return reactorCommandGateway.send(new AddProductCommand(
                IngredientId.of(productRequestData.ingredientId()),
                ProductId.create(),
                productRequestData.productName(),
                productRequestData.brand(),
                requireNonNullElseGet(productRequestData.stores(), Collections::emptySet),
                productRequestData.unitQuantity(),
                requireNonNullElseGet(productRequestData.nutritionalFacts(), Collections::emptyMap),
                requireNonNullElseGet(productRequestData.allergens(), Collections::emptySet)
        ));
    }

}