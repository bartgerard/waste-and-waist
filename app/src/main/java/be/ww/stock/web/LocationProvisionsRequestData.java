package be.ww.stock.web;

import java.math.BigDecimal;

public record LocationProvisionsRequestData(
		BigDecimal amount,
		String unit
) {
}
