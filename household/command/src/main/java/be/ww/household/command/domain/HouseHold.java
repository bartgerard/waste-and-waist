package be.ww.household.command.domain;

import be.ww.household.api.command.AddMemberCommand;
import be.ww.household.api.command.JoinHouseHoldCommand;
import be.ww.household.api.command.StartHouseHoldCommand;
import be.ww.household.api.event.HouseHoldJoinedEvent;
import be.ww.household.api.event.HouseHoldStartedEvent;
import be.ww.household.api.event.MemberAdded;
import be.ww.shared.type.HouseHoldId;
import be.ww.shared.type.MemberId;
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

@Aggregate
public class HouseHold {
    private final Set<MemberId> members = new HashSet<>();

    @AggregateIdentifier
    private HouseHoldId houseHoldId;

    private HouseHold() {
        // no-op
    }

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    public void handle(final StartHouseHoldCommand command) {
        apply(new HouseHoldStartedEvent(
                command.houseHoldId(),
                command.houseHoldName()
        ));
        apply(new MemberAdded(
                command.houseHoldId(),
                MemberId.create(),
                command.memberName(),
                command.birthDate()
        ));
    }

    @CommandHandler
    public void handle(final AddMemberCommand command) {
        isTrue(LocalDate.now().isAfter(command.birthDate()), "birthDate in the future");

        apply(new MemberAdded(
                command.houseHoldId(),
                command.memberId(),
                command.memberName(),
                command.birthDate()
        ));
    }

    @CommandHandler
    public void handle(final JoinHouseHoldCommand command) {
        apply(new HouseHoldJoinedEvent(
                command.houseHoldId(),
                command.userId(),
                "todo"
        ));
    }

    @EventSourcingHandler
    public void on(final HouseHoldStartedEvent event) {
        this.houseHoldId = event.houseHoldId();
    }

    @EventSourcingHandler
    public void on(final MemberAdded event) {
        this.members.add(event.memberId());
    }

    @EventSourcingHandler
    public void on(final HouseHoldJoinedEvent event) {
        //this.members.add(event.userId());
    }

}
