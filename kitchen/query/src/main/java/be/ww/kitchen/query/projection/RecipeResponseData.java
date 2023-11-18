package be.ww.kitchen.query.projection;

import java.util.List;

import be.ww.kitchen.api.type.Instruction;
import be.ww.shared.type.RecipeId;
import lombok.Builder;

@Builder
public record RecipeResponseData(
		RecipeId recipeId,
		String name,
		List<Instruction> instructions,
		int portionSize
) {

}
