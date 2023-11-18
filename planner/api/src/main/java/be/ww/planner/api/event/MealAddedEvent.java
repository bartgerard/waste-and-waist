package be.ww.planner.api.event;

import be.ww.shared.type.MealId;
import be.ww.shared.type.RecipeId;
import be.ww.shared.type.ScheduleId;

public record MealAddedEvent(
        ScheduleId scheduleId,
        MealId mealId,
        RecipeId recipeId,
        int amountOfPeople
) {
}
