package be.ww.kitchen.api.command;

import java.util.List;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import be.ww.kitchen.api.type.Instruction;
import be.ww.shared.type.RecipeId;

public record CreateRecipeCommand(

		@TargetAggregateIdentifier
		RecipeId recipeId,
		String name,
		List<Instruction> instructions,
		int portionSize
) {
}
