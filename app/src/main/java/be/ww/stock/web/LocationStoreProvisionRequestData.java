package be.ww.stock.web;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LocationStoreProvisionRequestData(
		BigDecimal amount,
		String unit,
		LocalDate bestBeforeDay,
		LocalDate useByDay
) {
}
