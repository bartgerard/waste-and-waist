package be.ww.store.command.domain;

import be.ww.shared.type.ingredient.IngredientId;
import be.ww.store.api.command.AddProductCommand;
import be.ww.store.api.event.ProductAddedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateCreationPolicy;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.CreationPolicy;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
public class Ingredient {
    @AggregateIdentifier
    private IngredientId ingredientId;

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    public void handle(
            final AddProductCommand command
    ) {
        apply(new ProductAddedEvent(
                command.ingredientId(),
                command.productId(),
                command.productName(),
                command.brand(),
                command.stores(),
                command.unitQuantity(),
                command.nutritionalFacts(),
                command.allergens()
        ));
    }

    @EventHandler
    public void on(
            final ProductAddedEvent event
    ) {
        this.ingredientId = event.ingredientId();
    }
}
