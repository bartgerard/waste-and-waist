package be.ww.stock.api.event;

import java.util.List;

import be.ww.shared.type.LocationId;
import be.ww.stock.api.command.StoreProvisionsCommand;


public record ProvisionsStoredEvent(
		LocationId locationId,
		List<StoreProvisionsCommand.Product> provisions


) {


}
