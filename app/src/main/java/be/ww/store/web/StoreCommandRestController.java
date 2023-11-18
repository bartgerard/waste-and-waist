package be.ww.store.web;

import be.ww.household.api.command.StartHouseHoldCommand;
import be.ww.shared.type.HouseHoldId;
import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/house-holds")
@CrossOrigin
@RequiredArgsConstructor
public class StoreCommandRestController {

    private final ReactorCommandGateway reactorCommandGateway;

    @PostMapping
    public Mono<Object> register(
            @RequestBody final ProductRequestData productRequestData
    ) {
        final HouseHoldId houseHoldId = HouseHoldId.create();
        return reactorCommandGateway.send(new StartHouseHoldCommand(
                houseHoldId,
                productRequestData.houseHoldName(),
                productRequestData.userId(),
                productRequestData.memberName(),
                productRequestData.birthDate()
        ));
    }

}