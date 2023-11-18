package be.ww.planner.api.command;


import be.ww.shared.type.MealId;
import be.ww.shared.type.RecipeId;
import be.ww.shared.type.ScheduleId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record AddMealCommand(
        @TargetAggregateIdentifier
        ScheduleId scheduleId,
        MealId mealId,
        RecipeId recipeId,
        int amountOfPeople
) {
}
