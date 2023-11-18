package be.ww.kitchen.api.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import be.ww.shared.type.RecipeId;

public record AssignRecipeCommand(

		@TargetAggregateIdentifier
		RecipeId recipeId
) {
}
