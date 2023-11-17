package be.ww.household.command.domain;

import be.ww.household.api.command.AddMemberCommand;
import be.ww.household.api.command.JoinHouseHoldCommand;
import be.ww.household.api.command.StartHouseHoldCommand;
import be.ww.household.api.event.HouseHoldJoinedEvent;
import be.ww.household.api.event.HouseHoldStartedEvent;
import be.ww.household.api.event.MemberAdded;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateCreationPolicy;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.CreationPolicy;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.HashSet;
import java.util.Set;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class HouseHold {
    private final Set<String> members = new HashSet<>();

    @AggregateIdentifier
    private String houseHoldId;
    private String adminUserId;

    private HouseHold() {
        // no-op
    }

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    public void handle(final StartHouseHoldCommand command) {
        apply(new HouseHoldStartedEvent(
                command.houseHoldId(),
                command.houseHoldName(),
                command.userId(),
                command.memberName()
        ));
    }

    @CommandHandler
    public void handle(final AddMemberCommand command) {
        apply(new MemberAdded(
                command.houseHoldId(),
                command.memberName()
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
        this.adminUserId = event.userId();
        this.members.add(event.userId());
    }

    @EventSourcingHandler
    public void on(final MemberAdded event) {
        this.members.add(event.memberName());
    }

    @EventSourcingHandler
    public void on(final HouseHoldJoinedEvent event) {
        this.members.add(event.userId());
    }

}
