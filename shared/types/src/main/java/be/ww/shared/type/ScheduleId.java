package be.ww.shared.type;

import be.ww.shared.domain.api.AggregateId;

import java.time.YearMonth;

public record ScheduleId(
        String id
) implements AggregateId {
    public static ScheduleId from(
            final HouseHoldId houseHoldId,
            final YearMonth yearMonth
    ) {
        return new ScheduleId(
                "schedule-%s-%s".formatted(houseHoldId, yearMonth)
        );
    }

    public static ScheduleId of(
            final String id
    ) {
        return new ScheduleId(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
