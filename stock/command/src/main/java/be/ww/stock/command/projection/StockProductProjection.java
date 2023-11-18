package be.ww.stock.command.projection;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import be.ww.stock.command.repository.StockProductDocument;
import be.ww.stock.command.repository.StockProductRepository;
import be.ww.store.api.event.ProductAddedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@ProcessingGroup("ww-stock-command-StockProductProjection")
@RequiredArgsConstructor
public class StockProductProjection {
	private final StockProductRepository stockProductRepository;


	@EventHandler
	public void on(
			final ProductAddedEvent event
	) {
		log.info("Handle StorageFacilities added [{}]", event);
		stockProductRepository.save(StockProductDocument.builder()
				.productId(event.productId().id())
				.build());

	}

}
