package be.ww.store.query.repository;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

public record ProductField(
        @Field(name = "product_id", type = FieldType.Keyword, norms = false)
        String productId,
        @Field(name = "brand", type = FieldType.Keyword, norms = false)
        String brand
) {
}
