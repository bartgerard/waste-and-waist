package be.ww.stock.command.web;

import be.ww.shared.type.HouseHoldId;
import be.ww.shared.type.LocationId;
import be.ww.stock.api.command.AddLocationCommand;
import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@CrossOrigin
@RequiredArgsConstructor
public class StockCommandRestController {

    public static final int TIMEOUT_SECONDS = 5;
    private final ReactorCommandGateway reactorCommandGateway;
    private final ReactorQueryGateway reactorQueryGateway;

    @PostMapping("locations")
    public void addLocation(
            @RequestBody final LocationRequestData locationRequestData
    ) {
        final LocationId locationId = LocationId.create();
        reactorCommandGateway.send(new AddLocationCommand(
                        locationId,
                        HouseHoldId.of(locationRequestData.houseHoldId()),
                        locationRequestData.locationName()
                ))
                .block(); // TODO
    }

}