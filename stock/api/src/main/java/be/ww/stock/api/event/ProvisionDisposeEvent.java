package be.ww.stock.api.event;

import be.ww.shared.type.LocationId;
import be.ww.shared.type.ProductId;
import be.ww.shared.type.ProvisionId;

public record ProvisionDisposeEvent(
		LocationId locationId,
		ProductId productId,
		ProvisionId provisionId
) {

}
