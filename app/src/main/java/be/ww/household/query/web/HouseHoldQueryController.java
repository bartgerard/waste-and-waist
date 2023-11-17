package be.ww.household.query.web;

import be.ww.household.api.query.FindHouseHoldsForUser;
import be.ww.household.api.query.HouseHoldResponseData;
import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class HouseHoldQueryController {

    private final ReactorQueryGateway reactorQueryGateway;

    // Request-Response
    @MessageMapping("users.{userId}.house-holds")
    public Mono<HouseHoldResponseData> myHouseHolds(
            @DestinationVariable String userId
    ) {
        return reactorQueryGateway.query(
                new FindHouseHoldsForUser(userId),
                ResponseTypes.instanceOf(HouseHoldResponseData.class)
        );
    }

    // Request-Stream
    @MessageMapping("users.{userId}.house-holds")
    public Flux<HouseHoldResponseData> myHouseHolds_subscribe(
            @DestinationVariable String userId
    ) {
        return reactorQueryGateway.subscriptionQueryMany(
                new FindHouseHoldsForUser(userId),
                HouseHoldResponseData.class
        );
    }

}