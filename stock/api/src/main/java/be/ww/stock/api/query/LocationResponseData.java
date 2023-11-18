package be.ww.stock.api.query;

import java.util.Set;

import be.ww.shared.type.LocationId;
import lombok.Builder;

@Builder
public record LocationResponseData(
		LocationId locationId,
		Set<Appliance> appliances) {
	public record Appliance(
			String type
	) {
	}
}
