package be.ww.kitchen.api.query;

import be.ww.shared.type.RecipeId;

public record FindRecipeByIdQuery(
		RecipeId recipeId
) {

}
