package be.ww.household.api.command;

import be.ww.household.api.type.HouseHoldId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;

public record StartHouseHoldCommand(
        @TargetAggregateIdentifier
        HouseHoldId houseHoldId,
        String houseHoldName,
        String userId,
        String memberName,
        LocalDate birthDate
) {
}
