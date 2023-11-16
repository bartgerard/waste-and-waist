package be.ww.household.query.projection;

import be.ww.household.api.event.HouseHoldJoinedEvent;
import be.ww.household.api.event.HouseHoldStartedEvent;
import be.ww.household.api.event.MemberAdded;
import be.ww.household.api.query.FindHouseHoldById;
import be.ww.household.api.query.FindHouseHoldsForUser;
import be.ww.household.api.query.HouseHoldResponseData;
import be.ww.household.query.repository.HouseHoldDocument;
import be.ww.household.query.repository.HouseHoldRepository;
import be.ww.household.query.repository.MemberField;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toUnmodifiableSet;

@Component
@RequiredArgsConstructor
@Slf4j
@ProcessingGroup("ww-household-query-HouseHoldProjection")
public class HouseHoldProjection {

    private final HouseHoldRepository houseHoldRepository;

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
    }

    @QueryHandler
    public HouseHoldResponseData handle(
            final FindHouseHoldById query
    ) {
        return houseHoldRepository.findByHouseHoldIdIs(query.houseHoldId())
                .map(houseHold -> new HouseHoldResponseData.HouseHold(
                        houseHold.getHouseHoldId()
                ))
                .stream()
                .collect(collectingAndThen(
                        toUnmodifiableSet(),
                        HouseHoldResponseData::new
                ));
    }

    @QueryHandler
    public HouseHoldResponseData handle(
            final FindHouseHoldsForUser query
    ) {
        return houseHoldRepository.findByHouseHoldIdIs(query.userId()) // TODO
                .map(houseHold -> new HouseHoldResponseData.HouseHold(
                        houseHold.getHouseHoldId()
                ))
                .stream()
                .collect(collectingAndThen(
                        toUnmodifiableSet(),
                        HouseHoldResponseData::new
                ));
    }

}
