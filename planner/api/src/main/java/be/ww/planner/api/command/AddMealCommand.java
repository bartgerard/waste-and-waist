package be.ww.planner.api.command;


import be.ww.shared.type.MealId;
import be.ww.shared.type.ScheduleId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record AddMealCommand(
        @TargetAggregateIdentifier
        ScheduleId scheduleId,
        MealId mealId
) {
}
