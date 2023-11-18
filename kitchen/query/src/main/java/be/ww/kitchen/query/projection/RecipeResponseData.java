package be.ww.kitchen.query.projection;

import be.ww.shared.type.RecipeId;
import lombok.Builder;

@Builder
public record RecipeResponseData(
		RecipeId recipeId,
		String name
) {
}
