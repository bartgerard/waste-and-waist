package be.ww.store.query.repository;

import lombok.Builder;
import lombok.Singular;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Set;

@Builder
public record ProductField(
        @Field(name = "product_id", type = FieldType.Keyword, norms = false)
        String productId,
        @Field(name = "brand", type = FieldType.Keyword, norms = false)
        String brand,
        @Singular
        @Field(name = "stores", type = FieldType.Nested)
        Set<String> stores,
        @Singular
        @Field(name = "facts", type = FieldType.Nested)
        Set<NutritionalFactField> facts
) {
}
