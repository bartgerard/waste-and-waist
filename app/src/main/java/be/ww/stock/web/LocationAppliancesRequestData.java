package be.ww.stock.web;

import java.util.Set;

import be.ww.stock.api.type.Appliance;

public record LocationAppliancesRequestData(
		Set<Appliance> appliances
) {
}
