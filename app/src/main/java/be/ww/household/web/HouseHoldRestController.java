package be.ww.household.web;

import be.ww.household.api.command.AddMemberCommand;
import be.ww.household.api.command.RemoveMemberCommand;
import be.ww.household.api.command.StartHouseHoldCommand;
import be.ww.household.api.query.FindHouseHoldByIdQuery;
import be.ww.household.api.query.FindHouseHoldsForUserQuery;
import be.ww.household.api.query.HouseHoldResponseData;
import be.ww.shared.type.HouseHoldId;
import be.ww.shared.type.MemberId;
import be.ww.shared.type.UserId;
import be.ww.stock.api.query.FindLocationsByHouseHoldIdQuery;
import be.ww.stock.api.query.LocationsResponseData;
import lombok.RequiredArgsConstructor;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;

import java.time.Duration;

@RestController
@RequestMapping(path = "/house-holds")
@CrossOrigin
@RequiredArgsConstructor
public class HouseHoldRestController {

    public static final int TIMEOUT_SECONDS = 5;
    private final ReactorCommandGateway reactorCommandGateway;
    private final ReactorQueryGateway reactorQueryGateway;

    @PostMapping
    public Mono<HouseHoldResponseData> register(
            @RequestBody final HouseHoldRequestData houseHoldRequestData
    ) {
        final HouseHoldId houseHoldId = HouseHoldId.create();
        return reactorCommandGateway.send(new StartHouseHoldCommand(
                        houseHoldId,
                        houseHoldRequestData.houseHoldName(),
                        houseHoldRequestData.userId(),
                        houseHoldRequestData.memberName(),
                        houseHoldRequestData.birthDate()
                ))
                .transform(objectMono -> Mono.zip(
                                        objectMono.subscribeOn(Schedulers.parallel()),
                                        accountSubscriptionQuery(houseHoldId).subscribeOn(Schedulers.parallel())
                                )
                                .map(Tuple2::getT2)
                );
    }

    @PostMapping("{houseHoldId}/members")
    public Mono<HouseHoldResponseData> register(
            @PathVariable final String houseHoldId,
            @RequestBody final MemberRequestData memberRequestData
    ) {
        final MemberId memberId = MemberId.create();
        return reactorCommandGateway.send(new AddMemberCommand(
                        HouseHoldId.of(houseHoldId),
                        memberId,
                        memberRequestData.name(),
                        memberRequestData.birthDate()
                ))
                .transform(objectMono -> Mono.zip(
                                        objectMono.subscribeOn(Schedulers.parallel()),
                                        accountSubscriptionQuery(HouseHoldId.of(houseHoldId)).subscribeOn(Schedulers.parallel())
                                )
                                .map(Tuple2::getT2)
                );
    }

    @GetMapping("{houseHoldId}")
    public Mono<HouseHoldResponseData> findByHouseHoldId(
            @PathVariable final String houseHoldId
    ) {
        return reactorQueryGateway.query(
                new FindHouseHoldByIdQuery(HouseHoldId.of(houseHoldId)),
                ResponseTypes.instanceOf(HouseHoldResponseData.class)
        );
    }

    @GetMapping("by-user/{userId}")
    public Mono<HouseHoldResponseData> findByUserId(
            @PathVariable final String userId
    ) {
        return reactorQueryGateway.query(
                new FindHouseHoldsForUserQuery(UserId.of(userId)),
                ResponseTypes.instanceOf(HouseHoldResponseData.class)
        );
    }

    @GetMapping("{houseHoldId}/locations")
    public Mono<LocationsResponseData> findLocationsByHouseHoldId(
            @PathVariable final String houseHoldId
    ) {
        return reactorQueryGateway.query(
                new FindLocationsByHouseHoldIdQuery(HouseHoldId.of(houseHoldId)),
                ResponseTypes.instanceOf(LocationsResponseData.class)
        );
    }

    @DeleteMapping("{houseHoldId}/members/{memberId}")
    public void removeMember(
            @PathVariable final HouseHoldId houseHoldId,
            @PathVariable final MemberId memberId
    ) {
        reactorCommandGateway.send(new RemoveMemberCommand(
                        houseHoldId,
                        memberId
                ))
                .block();
    }


    private Mono<HouseHoldResponseData> accountSubscriptionQuery(
            final HouseHoldId houseHoldId
    ) {
        final Flux<HouseHoldResponseData> queryResult = reactorQueryGateway.queryUpdates(
                new FindHouseHoldByIdQuery(houseHoldId),
                ResponseTypes.instanceOf(HouseHoldResponseData.class)
        );

        return queryResult
                .next()
                .timeout(Duration.ofSeconds(TIMEOUT_SECONDS));
    }
}