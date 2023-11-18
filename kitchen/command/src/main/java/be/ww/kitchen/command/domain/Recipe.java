package be.ww.kitchen.command.domain;


import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateCreationPolicy;
import org.axonframework.modelling.command.CreationPolicy;
import org.axonframework.spring.stereotype.Aggregate;

import be.ww.kitchen.api.command.CreateRecipeCommand;
import be.ww.kitchen.api.command.DeleteRecipeCommand;
import be.ww.kitchen.api.event.RecipeCreatedEvent;
import be.ww.kitchen.api.event.RecipeDeletedEvent;
import be.ww.shared.type.RecipeId;
import lombok.NoArgsConstructor;

@Aggregate
@NoArgsConstructor
public class Recipe {
	private RecipeId recipeId;

	@CommandHandler
	@CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
	public void handle(
			CreateRecipeCommand command
	) {
		apply(new RecipeCreatedEvent(
				command.recipeId(),
				command.name()
		));
	}

	@CommandHandler
	public void handle(
			DeleteRecipeCommand command
	) {
		apply(new RecipeDeletedEvent(
				command.recipeId()
		));
	}

	@EventHandler
	public void on(
			final RecipeCreatedEvent event
	) {
		this.recipeId = event.recipeId();
	}

	@EventHandler
	public void on(
			RecipeDeletedEvent event) {
		this.recipeId = event.recipeId();
	}
}
