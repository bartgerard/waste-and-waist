package be.ww.stock.api.event;

import java.time.LocalDate;

import be.ww.shared.type.LocationId;
import be.ww.shared.type.ProductId;
import be.ww.shared.type.ProvisionId;
import be.ww.shared.type.ingredient.IngredientId;
import be.ww.shared.type.ingredient.Quantity;


public record ProvisionsStoredEvent(
		LocationId locationId,
		ProductId productId,
		ProvisionId provisionId,
		IngredientId ingredientId,
		Quantity quantity,
		LocalDate bestBefore,
		LocalDate usedBy


) {

}
