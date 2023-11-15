package be.ww.household.query.projection;

import be.ww.household.adapter.repository.HouseHoldDocument;
import be.ww.household.adapter.repository.HouseHoldRepository;
import be.ww.household.adapter.repository.MemberField;
import be.ww.household.api.event.HouseHoldJoinedEvent;
import be.ww.household.api.event.HouseHoldStartedEvent;
import be.ww.household.api.event.MemberAdded;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.springframework.stereotype.Component;

import java.util.List;

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

}
