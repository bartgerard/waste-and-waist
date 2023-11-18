package be.ww.household.api.query;

import be.ww.shared.type.HouseHoldId;

public record FindHouseHoldByIdQuery(
        HouseHoldId houseHoldId
) {
}
