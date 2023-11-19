package be.ww.kitchen.api.event;

import java.util.List;

import be.ww.kitchen.api.type.Instruction;
import be.ww.shared.type.RecipeId;

public record RecipeCreatedEvent(
		RecipeId recipeId,
		String name,
		List<Instruction> instructions,
		int portionSize
) {
}
