package be.ww.household.api.query;

import be.ww.shared.type.UserId;

public record FindHouseHoldsForUserQuery(
        UserId userId
) {
}
