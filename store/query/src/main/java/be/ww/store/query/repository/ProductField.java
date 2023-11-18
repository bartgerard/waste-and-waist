package be.ww.store.query.repository;

import be.ww.shared.type.ingredient.NutritionalFact;
import lombok.Builder;
import lombok.Singular;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Builder
public record ProductField(
        @Field(name = "product_id", type = FieldType.Keyword, norms = false)
        String productId,
        @Field(name = "name", type = FieldType.Keyword, norms = false)
        String name,
        @Field(name = "brand", type = FieldType.Keyword, norms = false)
        String brand,
        @Singular
        @Field(name = "stores", type = FieldType.Nested)
        Set<String> stores,
        @Field(name = "unit_quantity", type = FieldType.Object)
        QuantityField unitQuantity,
        @Singular
        @Field(name = "nutritional_facts", type = FieldType.Nested)
        Map<NutritionalFact, BigDecimal> nutritionalFacts
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductField that = (ProductField) o;
        return Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
