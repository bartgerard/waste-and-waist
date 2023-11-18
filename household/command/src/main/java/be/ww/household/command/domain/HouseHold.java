package be.ww.household.command.domain;

import be.ww.household.api.command.AddMemberCommand;
import be.ww.household.api.command.DisbandHouseHoldCommand;
import be.ww.household.api.command.JoinHouseHoldCommand;
import be.ww.household.api.command.RemoveMemberCommand;
import be.ww.household.api.command.StartHouseHoldCommand;
import be.ww.household.api.event.HouseHoldDisbandedEvent;
import be.ww.household.api.event.HouseHoldJoinedEvent;
import be.ww.household.api.event.HouseHoldStartedEvent;
import be.ww.household.api.event.MemberAddedEvent;
import be.ww.household.api.event.MemberRemovedEvent;
import be.ww.shared.type.HouseHoldId;
import be.ww.shared.type.MemberId;
import be.ww.shared.type.UserId;


import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateCreationPolicy;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.CreationPolicy;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.apache.commons.lang3.Validate.isTrue;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import static org.axonframework.modelling.command.AggregateLifecycle.markDeleted;

@Aggregate
public class HouseHold {
    private final Set<Member> members = new HashSet<>();

    @AggregateIdentifier
    private HouseHoldId houseHoldId;

    private HouseHold() {
        // no-op
    }

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    public void handle(final StartHouseHoldCommand command) {
        final MemberId memberId = MemberId.create();
        final UserId userId = UserId.of(command.userId());
        apply(new HouseHoldStartedEvent(
                command.houseHoldId(),
                command.houseHoldName()
        ));
        apply(new MemberAddedEvent(
                command.houseHoldId(),
                memberId,
                command.memberName(),
                command.birthDate()
        ));
        apply(new HouseHoldJoinedEvent(
                command.houseHoldId(),
                memberId,
                userId
        ));
    }

    @CommandHandler
    public void handle(final AddMemberCommand command) {
        isTrue(LocalDate.now().isAfter(command.birthDate()), "birthDate in the future");

        apply(new MemberAddedEvent(
                command.houseHoldId(),
                command.memberId(),
                command.memberName(),
                command.birthDate()
        ));
    }

    @CommandHandler
    public void handle(final RemoveMemberCommand command) {
        isTrue(isMember(command.memberId()), "member isn't part of the household");

        apply(new MemberRemovedEvent(
                command.houseHoldId(),
                command.memberId()
        ));
    }

    @CommandHandler
    public void handle(final JoinHouseHoldCommand command) {
        isTrue(isMember(command.memberId()), "member isn't part of the household");
        isTrue(!getUsers().contains(command.userId()), "user already in household");

        apply(new HouseHoldJoinedEvent(
                command.houseHoldId(),
                command.memberId(),
                command.userId()
        ));
    }

    @CommandHandler
    public void handle(final DisbandHouseHoldCommand command) {
        apply(new HouseHoldDisbandedEvent(
                command.houseHoldId()
        ));
    }

    @EventSourcingHandler
    public void on(final HouseHoldStartedEvent event) {
        this.houseHoldId = event.houseHoldId();
    }

    @EventSourcingHandler
    public void on(final MemberAddedEvent event) {
        this.members.add(new Member(event.memberId()));
    }

    @EventSourcingHandler
    public void on(final MemberRemovedEvent event) {
        members.removeIf(member -> member.memberId().equals(event.memberId()));

        if (members.isEmpty()) {
            markDeleted();
        }

    }

    @EventSourcingHandler
    public void on(final HouseHoldJoinedEvent event) {
        //this.members.add(event.userId());
    }

    @EventSourcingHandler
    public void on(final HouseHoldDisbandedEvent event) {
        markDeleted();
    }
    public Set<UserId> getUsers() {
        Set<UserId> userSet = new HashSet<>();
        for (Member member : members) {
            userSet.add(member.userId());
        }
        return userSet;
    }

    public boolean isMember(MemberId memberId) {
        return members.stream()
                .anyMatch(member -> member.memberId().equals(memberId));
    }


}
