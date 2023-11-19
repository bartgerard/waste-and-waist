package be.ww.stock.api.query;

import be.ww.shared.type.HouseHoldId;

public record FindLocationsByHouseHoldIdQuery(
        HouseHoldId houseHoldId
) {

}
