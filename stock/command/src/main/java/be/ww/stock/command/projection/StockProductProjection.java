package be.ww.stock.command.projection;

import java.math.BigDecimal;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import be.ww.shared.type.ingredient.Quantity;
import be.ww.stock.api.event.ProvisionDisposeEvent;
import be.ww.stock.api.event.ProvisionsConsumedEvent;
import be.ww.stock.api.event.ProvisionsStoredEvent;
import be.ww.stock.command.repository.StockProductDocument;
import be.ww.stock.command.repository.StockProductRepository;
import lombok.RequiredArgsConstructor;

@Component
@ProcessingGroup("ww-stock-command-StockProductProjection")
@RequiredArgsConstructor
public class StockProductProjection {
	private final StockProductRepository stockProductRepository;

	@EventHandler
	public void on(
			final ProvisionsStoredEvent event
	) {
		stockProductRepository.save(StockProductDocument.builder()
				.productId(event.productId().id())
				.ingredientId(event.ingredientId().id())
				.quantity(event.quantity())
				.bestBefore(event.bestBefore())
				.usedBy(event.usedBy())
				.build()
		);

	}

	@EventHandler
	public void on(
			final ProvisionsConsumedEvent event
	) throws IllegalAccessException {
		StockProductDocument product = stockProductRepository.findByProductId(event.productId().id())
				.orElseThrow(() -> new IllegalAccessException("Can't reduce stock that is not present"));
		Quantity leftOverQuantity = product.getQuantity().reduce(event.quantity());
		if (leftOverQuantity.amount().value().equals(BigDecimal.ZERO)) {
			stockProductRepository.delete(product);
		} else {
			stockProductRepository.save(StockProductDocument.builder()
					.productId(event.productId().id())
					.provisionId(event.provisionId().id())
					.quantity(leftOverQuantity)
					.build()
			);
		}
	}

	@EventHandler
	public void on(
			final ProvisionDisposeEvent event
	) throws IllegalAccessException {
		StockProductDocument toDeleteProvision = stockProductRepository.findByProductId(event.productId().id())
				.orElseThrow(() -> new IllegalAccessException("Can't reduce stock that is not present"));
		stockProductRepository.delete(toDeleteProvision);
	}
}
