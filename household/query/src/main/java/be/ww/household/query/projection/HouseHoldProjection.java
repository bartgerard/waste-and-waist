package be.ww.household.query.projection;

import be.ww.household.api.event.HouseHoldJoinedEvent;
import be.ww.household.api.event.HouseHoldStartedEvent;
import be.ww.household.api.event.MemberAdded;
import be.ww.household.api.query.FindHouseHoldById;
import be.ww.household.api.query.FindHouseHoldsForUser;
import be.ww.household.api.query.HouseHoldResponseData;
import be.ww.household.api.type.HouseHoldId;
import be.ww.household.query.repository.HouseHoldDocument;
import be.ww.household.query.repository.HouseHoldRepository;
import be.ww.household.query.repository.MemberField;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;

import java.util.List;
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

    @EventHandler
    public void on(
            final HouseHoldStartedEvent event,
            final UnitOfWork<?> unitOfWork
    ) {
        log.info("Handle HouseHoldStartedEvent [{}]", event);
        houseHoldRepository.save(HouseHoldDocument.builder()
                .houseHoldId(event.houseHoldId())
                .members(List.of(MemberField.of(
                        event.memberName(),
                        event.userId()
                )))
                .build()
        );

        emitUpdateForHouseHoldId(HouseHoldId.of(event.houseHoldId()));
        emitUpdateForUser(event.userId());
    }

    @EventHandler
    public void on(
            final MemberAdded event,
            final UnitOfWork<?> unitOfWork
    ) {
        log.info("Handle MemberAdded [{}]", event);
        houseHoldRepository.findByHouseHoldIdIs(event.houseHoldId())
                .map(houseHoldDocument -> houseHoldDocument.toBuilder()
                        .member(MemberField.of(
                                event.memberName(),
                                null
                        ))
                        .build()
                )
                .ifPresent(houseHoldRepository::save);

        emitUpdateForHouseHoldId(HouseHoldId.of(event.houseHoldId()));
    }

    @EventHandler
    public void on(
            final HouseHoldJoinedEvent event,
            final UnitOfWork<?> unitOfWork
    ) {
        log.info("Handle HouseHoldJoinedEvent [{}]", event);
        houseHoldRepository.findByHouseHoldIdIs(event.houseHoldId())
                .map(houseHoldDocument -> houseHoldDocument.toBuilder()
                        .member(MemberField.of(
                                event.memberName(),
                                event.userId()
                        ))
                        .build()
                )
                .ifPresent(houseHoldRepository::save);

        emitUpdateForHouseHoldId(HouseHoldId.of(event.houseHoldId()));
        emitUpdateForUser(event.userId());
    }

    @QueryHandler
    public HouseHoldResponseData handle(
            final FindHouseHoldById query
    ) {
        return houseHoldRepository.findByHouseHoldIdIs(query.houseHoldId())
                .stream()
                .map(houseHold -> new HouseHoldResponseData.HouseHold(
                        houseHold.getHouseHoldId()
                ))
                .collect(collectingAndThen(
                        toUnmodifiableSet(),
                        HouseHoldResponseData::new
                ));
    }

    @QueryHandler
    public HouseHoldResponseData handle(
            final FindHouseHoldsForUser query
    ) {
        return houseHoldRepository.findAllByUserId(query.userId())
                .stream()
                .map(houseHold -> new HouseHoldResponseData.HouseHold(
                        houseHold.getHouseHoldId()
                ))
                .collect(collectingAndThen(
                        toUnmodifiableSet(),
                        HouseHoldResponseData::new
                ));
    }

    private void emitUpdateForUser(
            final String userId
    ) {
        queryUpdateEmitter.emit(
                FindHouseHoldsForUser.class,
                query -> Objects.equals(query.userId(), userId),
                handle(new FindHouseHoldsForUser(userId))
        );
    }

    private void emitUpdateForHouseHoldId(
            final HouseHoldId houseHoldId
    ) {
        queryUpdateEmitter.emit(
                FindHouseHoldById.class,
                query -> Objects.equals(query.houseHoldId(), houseHoldId.id()),
                handle(new FindHouseHoldById(houseHoldId.id()))
        );
    }

}
