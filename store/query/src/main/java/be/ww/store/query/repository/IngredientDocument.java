package be.ww.store.query.repository;

import be.ww.shared.type.ingredient.AmountRange;
import be.ww.shared.type.ingredient.NutritionalFact;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.ValueConverter;

import java.util.Map;
import java.util.Set;

@Document(indexName = "ww-store-query-ingredient")
@Value
@Builder(toBuilder = true)
public class IngredientDocument {
    @Id
    String id;

    @EqualsAndHashCode.Include
    @Field(name = "ingredient_id", type = FieldType.Keyword, norms = false)
    String ingredientId;

    @Singular
    @Field(name = "products", type = FieldType.Nested)
    Set<ProductField> products;

    @Singular
    @Field(name = "ranged_nutritional_facts", type = FieldType.Flattened)
    @ValueConverter(AmountRangeConverter.class)
    Map<NutritionalFact, AmountRange> nutritionalFacts;
}
