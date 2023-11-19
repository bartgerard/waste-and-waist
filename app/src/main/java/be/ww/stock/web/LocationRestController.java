package be.ww.stock.web;

import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ww.shared.type.Amount;
import be.ww.shared.type.HouseHoldId;
import be.ww.shared.type.LocationId;
import be.ww.shared.type.ProductId;
import be.ww.shared.type.ProvisionId;
import be.ww.shared.type.ingredient.BestBeforeDay;
import be.ww.shared.type.ingredient.IngredientId;
import be.ww.shared.type.ingredient.Quantity;
import be.ww.shared.type.ingredient.UseByDay;
import be.ww.stock.api.command.AddAppliancesCommand;
import be.ww.stock.api.command.AddLocationCommand;
import be.ww.stock.api.command.AddStorageFacilitiesCommand;
import be.ww.stock.api.command.ConsumeProvisionsCommand;
import be.ww.stock.api.command.DisposeProvisionsCommand;
import be.ww.stock.api.command.RemoveAppliancesCommand;
import be.ww.stock.api.command.RemoveLocationCommand;
import be.ww.stock.api.command.StoreProvisionsCommand;
import be.ww.stock.api.query.FindLocationByIdQuery;
import be.ww.stock.api.query.LocationResponseData;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("locations")
@RequiredArgsConstructor
public class LocationRestController {

	public static final int TIMEOUT_SECONDS = 5;
	private final ReactorCommandGateway reactorCommandGateway;
	private final ReactorQueryGateway reactorQueryGateway;

	@PostMapping
	public Mono<Object> addLocation(
			@RequestBody final LocationRequestData locationRequestData
	) {
		final LocationId locationId = LocationId.create();
		return reactorCommandGateway.send(new AddLocationCommand(
						locationId,
						HouseHoldId.of(locationRequestData.houseHoldId()),
						locationRequestData.locationName()
				))
				.subscribeOn(Schedulers.parallel());
	}

	@PostMapping("{locationId}/addAppliances")
	public Mono<Object> addAppliances(
			@PathVariable final String locationId,
			@RequestBody final LocationAppliancesRequestData request
	) {
		return reactorCommandGateway.send(new AddAppliancesCommand(
						LocationId.of(locationId),
						request.appliances()
				))
				.subscribeOn(Schedulers.parallel());
	}

	@PostMapping("{locationId}/addFacilities")
	public Mono<Object> addStorageFacilitiesCommand(
			@PathVariable final String locationId,
			@RequestBody final LocationFacilitiesRequestData request
	) {
		return reactorCommandGateway.send(new AddStorageFacilitiesCommand(
						LocationId.of(locationId),
						request.storageFacilities()
				))
				.subscribeOn(Schedulers.parallel());
	}

	@PostMapping("{locationId}/products/{productId}/provisions/{provisionId}")
	public Mono<Object> consumeProvisions(
			@PathVariable final String locationId,
			@PathVariable final String productId,
			@PathVariable final String provisionId,
			@RequestBody final LocationProvisionsRequestData request
	) {
		return reactorCommandGateway.send(new ConsumeProvisionsCommand(
						LocationId.of(locationId),
						ProductId.of(productId),
						ProvisionId.of(provisionId),
						new Quantity(Amount.of(request.amount()), Quantity.Unit.valueOf(request.unit()))
				))
				.subscribeOn(Schedulers.parallel());
	}

	@DeleteMapping("{locationId}/products/{productId}/provisions/{provisionId}")
	public void disposeProvisions(
			@PathVariable final String locationId,
			@PathVariable final String productId,
			@PathVariable final String provisionId
	) {
		reactorCommandGateway.send(new DisposeProvisionsCommand(
						LocationId.of(locationId),
						ProductId.of(productId),
						ProvisionId.of(provisionId)
				))
				.subscribeOn(Schedulers.parallel());
	}

	@DeleteMapping("{locationId}/deleteAppliances")
	public void removeAppliances(
			@PathVariable final String locationId,
			@RequestBody final LocationAppliancesRequestData request
	) {
		reactorCommandGateway.send(new RemoveAppliancesCommand(
						LocationId.of(locationId),
						request.appliances()
				))
				.subscribeOn(Schedulers.parallel());
	}

	@DeleteMapping("{locationId}")
	public void removeLocation(
			@PathVariable final String locationId
	) {
		reactorCommandGateway.send(new RemoveLocationCommand(
						LocationId.of(locationId)
				))
				.subscribeOn(Schedulers.parallel());
	}

	@PostMapping("{locationId}/products/{productId}/ingredients/{ingredientId}/provisions/{provisionId}")
	public Mono<Object> consumeProvisions(
			@PathVariable final String locationId,
			@PathVariable final String productId,
			@PathVariable final String provisionId,
			@PathVariable final String ingredientId,
			@RequestBody final LocationStoreProvisionRequestData request
	) {
		return reactorCommandGateway.send(new StoreProvisionsCommand(
						LocationId.of(locationId),
						ProductId.of(productId),
						ProvisionId.of(provisionId),
						IngredientId.of(ingredientId),
						new Quantity(Amount.of(request.amount()), Quantity.Unit.valueOf(request.unit())),
						new BestBeforeDay(request.bestBeforeDay()),
						new UseByDay(request.useByDay())

				))
				.subscribeOn(Schedulers.parallel());
	}

	@GetMapping("{locationId}")
	public Mono<LocationResponseData> findByLocationId(
			@PathVariable final String locationId
	) {
		return reactorQueryGateway.query(
				new FindLocationByIdQuery(LocationId.of(locationId)),
				ResponseTypes.instanceOf(LocationResponseData.class)
		);
	}

}