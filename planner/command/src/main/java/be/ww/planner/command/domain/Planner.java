package be.ww.planner.command.domain;

import be.ww.planner.api.command.AddMealCommand;
import be.ww.planner.api.event.MealAddedEvent;
import be.ww.shared.type.MealId;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.HashSet;
import java.util.Set;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Planner {

    private final Set<MealId> meals = new HashSet<>();


    @CommandHandler
    public void handle(final AddMealCommand command) {
        // check of meal niet mag toegevoegd worden
        apply(new MealAddedEvent(
                command.scheduleId(),
                command.mealId(),
                command.recipeId(),
                command.amountOfPeople()
        ));
    }

    @EventSourcingHandler
    public void on(final MealAddedEvent event) {
        this.meals.add(event.mealId());
    }
}
