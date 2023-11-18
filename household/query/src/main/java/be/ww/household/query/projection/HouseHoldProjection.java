package be.ww.household.query.projection;

import be.ww.household.api.event.HouseHoldJoinedEvent;
import be.ww.household.api.event.HouseHoldStartedEvent;
import be.ww.household.api.event.MemberAddedEvent;
import be.ww.household.api.query.FindHouseHoldByIdQuery;
import be.ww.household.api.query.FindHouseHoldsForUserQuery;
import be.ww.household.api.query.HouseHoldResponseData;
import be.ww.household.query.repository.HouseHoldDocument;
import be.ww.household.query.repository.HouseHoldRepository;
import be.ww.household.query.repository.MemberField;
import be.ww.shared.type.HouseHoldId;
import be.ww.shared.type.UserId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toUnmodifiableSet;

@Component
@RequiredArgsConstructor
@Slf4j
@ProcessingGroup("ww-household-query-HouseHoldProjection")
public class HouseHoldProjection {

    private final HouseHoldRepository houseHoldRepository;
    private final QueryUpdateEmitter queryUpdateEmitter;

    private static HouseHoldResponseData.HouseHold map(
            final HouseHoldDocument houseHold
    ) {
        return new HouseHoldResponseData.HouseHold(
                houseHold.getHouseHoldId(),
                houseHold.getMembers()
                        .stream()
                        .map(member -> new HouseHoldResponseData.Member(
                                member.memberId(),
                                member.name(),
                                member.birthDate()
                        ))
                        .toList()
        );
    }

    @EventHandler
    public void on(
            final HouseHoldStartedEvent event,
            final UnitOfWork<?> unitOfWork
    ) {
        log.info("Handle HouseHoldStartedEvent [{}]", event);
        houseHoldRepository.save(HouseHoldDocument.builder()
                .houseHoldId(event.houseHoldId().id())
                .houseHoldName(event.houseHoldName())
                .build()
        );

        emitUpdateForHouseHoldId(event.houseHoldId());
    }

    @EventHandler
    public void on(
            final MemberAddedEvent event
    ) {
        log.info("Handle MemberAdded [{}]", event);
        houseHoldRepository.findByHouseHoldIdIs(event.houseHoldId().id())
                .map(houseHoldDocument -> houseHoldDocument.toBuilder()
                        .member(MemberField.of(
                                event.memberId().id(),
                                event.memberName(),
                                event.birthDate(),
                                null
                        ))
                        .build()
                )
                .ifPresent(houseHoldRepository::save);

        emitUpdateForHouseHoldId(event.houseHoldId());
    }

    @EventHandler
    public void on(
            final HouseHoldJoinedEvent event
    ) {
        log.info("Handle HouseHoldJoinedEvent [{}]", event);
        /*
        houseHoldRepository.findByHouseHoldIdIs(event.houseHoldId().id())
                .map(houseHoldDocument -> houseHoldDocument.toBuilder()
                        .member(MemberField.of(
                                event.memberName(),
                                event.userId()
                        ))
                        .build()
                )
                .ifPresent(houseHoldRepository::save);

         */

        emitUpdateForHouseHoldId(event.houseHoldId());
        emitUpdateForUser(event.userId());
    }

    @QueryHandler
    public HouseHoldResponseData handle(
            final FindHouseHoldByIdQuery query
    ) {
        return houseHoldRepository.findByHouseHoldIdIs(query.houseHoldId().id())
                .stream()
                .map(HouseHoldProjection::map)
                .collect(collectingAndThen(
                        toUnmodifiableSet(),
                        HouseHoldResponseData::new
                ));
    }

    @QueryHandler
    public HouseHoldResponseData handle(
            final FindHouseHoldsForUserQuery query
    ) {
        return houseHoldRepository.findAllByUserId(query.userId().id())
                .stream()
                .map(HouseHoldProjection::map)
                .collect(collectingAndThen(
                        toUnmodifiableSet(),
                        HouseHoldResponseData::new
                ));
    }

    private void emitUpdateForUser(
            final UserId userId
    ) {
        queryUpdateEmitter.emit(
                FindHouseHoldsForUserQuery.class,
                query -> Objects.equals(query.userId(), userId),
                handle(new FindHouseHoldsForUserQuery(userId))
        );
    }

    private void emitUpdateForHouseHoldId(
            final HouseHoldId houseHoldId
    ) {
        queryUpdateEmitter.emit(
                FindHouseHoldByIdQuery.class,
                query -> Objects.equals(query.houseHoldId(), houseHoldId),
                handle(new FindHouseHoldByIdQuery(houseHoldId))
        );
    }

}
