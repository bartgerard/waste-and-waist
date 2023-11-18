package be.ww.kitchen.api.event;

import be.ww.shared.type.RecipeId;

public record RecipeDeletedEvent(
		RecipeId recipeId
) {
}
