package be.ww.household.query.projection;

import be.ww.household.api.event.HouseHoldJoinedEvent;
import be.ww.household.api.event.HouseHoldStartedEvent;
import be.ww.household.api.event.MemberAddedEvent;
import be.ww.household.api.event.MemberRemovedEvent;
import be.ww.household.api.query.FindHouseHoldByIdQuery;
import be.ww.household.api.query.FindHouseHoldsForUserQuery;
import be.ww.household.api.query.HouseHoldResponseData;
import be.ww.household.query.repository.HouseHoldDocument;
import be.ww.household.query.repository.HouseHoldRepository;
import be.ww.household.query.repository.MemberField;
import be.ww.shared.type.HouseHoldId;
import be.ww.shared.type.MemberId;
import be.ww.shared.type.UserId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;

import static java.util.Collections.emptySet;
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
                HouseHoldId.of(houseHold.getHouseHoldId()),
                houseHold.getName(),
                houseHold.getMembers()
                        .stream()
                        .map(member -> new HouseHoldResponseData.Member(
                                MemberId.of(member.memberId()),
                                member.name(),
                                member.birthDate()
                        ))
                        .toList()
        );
    }

    @EventHandler
    public void on(
            final HouseHoldStartedEvent event
    ) {
        log.info("Handle HouseHoldStartedEvent [{}]", event);
        houseHoldRepository.save(HouseHoldDocument.builder()
                .houseHoldId(event.houseHoldId().id())
                .name(event.houseHoldName())
                .members(emptySet())
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
                .map(houseHoldDocument -> {
                    final HashSet<MemberField> members = new HashSet<>(houseHoldDocument.getMembers());
                    final MemberField alteredMember = members.stream()
                            .filter(member -> Objects.equals(member.memberId(), event.memberId().id()))
                            .findFirst()
                            .orElseGet(() -> MemberField.of(
                                    event.memberId().id(),
                                    event.memberName(),
                                    event.birthDate(),
                                    null
                            ));
                    members.add(alteredMember);

                    return houseHoldDocument.toBuilder()
                            .members(members)
                            .build();
                })
                .ifPresent(houseHoldRepository::save);

        emitUpdateForHouseHoldId(event.houseHoldId());
    }

    @EventHandler
    public void on(
            final MemberRemovedEvent event
    ) {
        log.info("Handle MemberRemoved [{}]", event);
        houseHoldRepository.findByHouseHoldIdIs(event.houseHoldId().id())
                .map(houseHoldDocument -> houseHoldDocument.toBuilder()
                        .members(houseHoldDocument.getMembers()
                                .stream()
                                .filter(member -> !member.memberId().equals(event.memberId().id()))
                                .collect(toUnmodifiableSet())
                        )
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
        houseHoldRepository.findByHouseHoldIdIs(event.houseHoldId().id())
                .map(houseHoldDocument -> {
                    System.out.println(houseHoldDocument);
                    return houseHoldDocument.toBuilder()
                            .members(houseHoldDocument.getMembers()
                                    .stream()
                                    .map(member -> Objects.equals(member.memberId(), event.memberId().id())
                                            ? MemberField.of(member.memberId(), member.name(), member.birthDate(), event.userId().id())
                                            : member
                                    )
                                    .collect(toUnmodifiableSet())
                            )
                            .build();
                })
                .ifPresent(houseHoldRepository::save);

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
