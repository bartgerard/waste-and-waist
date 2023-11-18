package be.ww.kitchen.query.projection;

import java.util.Objects;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;

import be.ww.kitchen.api.command.DeleteRecipeCommand;
import be.ww.kitchen.api.event.RecipeCreatedEvent;
import be.ww.kitchen.api.query.FindRecipeByIdQuery;
import be.ww.kitchen.query.repository.KitchenDocument;
import be.ww.kitchen.query.repository.KitchenRepository;
import be.ww.shared.type.RecipeId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
@ProcessingGroup("ww-kitchen-query-KitchenProjection")
public class KitchenProjection {
	private final KitchenRepository kitchenRepository;
	private final QueryUpdateEmitter queryUpdateEmitter;


	@QueryHandler
	public RecipeResponseData handle(
			final FindRecipeByIdQuery query
	) {
		return kitchenRepository.findByRecipeId(query.recipeId().id())
				.map(recipe -> RecipeResponseData
						.builder()
						.recipeId(RecipeId.of(recipe.getRecipeId()))
						.name(recipe.getName())
						.build()
				).orElseThrow((() -> new IllegalArgumentException("Can't find Recipe")));
	}

	@EventHandler
	public void on(
			final RecipeCreatedEvent event
	) {
		log.info("RecipeCreated event started [{}]", event);
		kitchenRepository.save(KitchenDocument.builder()
				.recipeId(event.recipeId().id())
				.name(event.name())
				.build()
		);
		emitUpdateForRecipeId(event.recipeId());
	}

	@EventHandler
	public void on(
			final DeleteRecipeCommand event
	) {
		log.info("RecipeCreated deleted event [{}]", event);
		kitchenRepository.findByRecipeId(event.recipeId().id()).ifPresent(
				this.kitchenRepository::delete
		);
		emitUpdateForRecipeId(event.recipeId());
	}

	private void emitUpdateForRecipeId(
			final RecipeId recipeId
	) {
		queryUpdateEmitter.emit(
				FindRecipeByIdQuery.class,
				query -> Objects.equals(query.recipeId(), recipeId),
				handle(new FindRecipeByIdQuery(recipeId))
		);
	}

}
