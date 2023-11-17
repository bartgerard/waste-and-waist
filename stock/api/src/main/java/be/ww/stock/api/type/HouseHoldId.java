package be.ww.stock.api.type;

public record HouseHoldId(
        String id
) {
    public static HouseHoldId of(
            final String id
    ) {
        return new HouseHoldId(id);
    }
}
