package be.ww.stock.web;

import java.util.Set;

public record LocationAppliancesRequestData(
		Set<String> appliances
) {
}
