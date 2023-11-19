package be.ww.stock.web;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public record LocationStoreProvisionRequestData(
		List<Product> products
) {
	record Product(String productId,
	               BigDecimal amount,
	               String unit,
	               LocalDate bestBeforeDay,
	               LocalDate useByDay) {
	}
}
