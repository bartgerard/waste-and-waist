package be.ww.kitchen.api.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import be.ww.shared.type.RecipeId;

public record CreateRecipeCommand(

		@TargetAggregateIdentifier
		RecipeId recipeId,

		String name
) {
}
