package be.ww.store.command.web;

import java.util.Set;

public record ProductRequestData(
        String ingredientId,
        String productId,
        String productName,
        String brand,
        Set<String> stores
) {
}
