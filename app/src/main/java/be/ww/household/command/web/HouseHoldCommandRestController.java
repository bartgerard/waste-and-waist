package be.ww.household.command.web;

import be.ww.household.api.command.StartHouseHoldCommand;
import be.ww.household.api.query.FindHouseHoldById;
import be.ww.household.api.query.HouseHoldResponseData;
import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.UUID;

@RestController
@RequestMapping(path = "/house-holds")
@CrossOrigin
@RequiredArgsConstructor
public class HouseHoldCommandRestController {

    public static final int TIMEOUT_SECONDS = 5;
    private final ReactorCommandGateway reactorCommandGateway;
    private final ReactorQueryGateway reactorQueryGateway;

    @GetMapping("ping")
    public String ping() {
        return "pong";
    }

    @PostMapping
    public Mono<HouseHoldResponseData> register(
            @RequestBody final HouseHoldRequestData houseHoldRequestData
    ) {
        final UUID houseHoldId = UUID.randomUUID();
        return reactorCommandGateway.send(new StartHouseHoldCommand(
                        houseHoldId.toString(),
                        houseHoldRequestData.houseHoldName(),
                        houseHoldRequestData.userId(),
                        houseHoldRequestData.memberName()
                ))
                .transform(objectMono -> Mono.zip(
                                        objectMono
                                                .subscribeOn(Schedulers.parallel()),
                                        accountSubscriptionQuery(houseHoldId)
                                                .subscribeOn(Schedulers.parallel())
                                )
                                .map(Tuple2::getT2)
                );
    }


    private Mono<HouseHoldResponseData> accountSubscriptionQuery(
            final UUID houseHoldId
    ) {
        final Flux<HouseHoldResponseData> queryResult = reactorQueryGateway.queryUpdates(
                new FindHouseHoldById(houseHoldId.toString()),
                ResponseTypes
                        .instanceOf(HouseHoldResponseData.class)
        );
        return queryResult
                .next()
                .timeout(Duration.ofSeconds(TIMEOUT_SECONDS));
    }
}