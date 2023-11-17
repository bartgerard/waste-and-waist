package be.ww.stock.command.projection;

import be.ww.household.api.event.HouseHoldStartedEvent;
import be.ww.stock.command.repository.StockHouseHoldDocument;
import be.ww.stock.command.repository.StockHouseHoldRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("ww-stock-command-StockHouseHoldProjection")
@RequiredArgsConstructor
public class StockHouseHoldProjection {
    private final StockHouseHoldRepository stockHouseHoldRepository;

    @EventHandler
    public void on(
            final HouseHoldStartedEvent event
    ) {
        stockHouseHoldRepository.save(StockHouseHoldDocument.builder()
                .houseHoldId(event.houseHoldId().id())
                .build()
        );
    }
}
