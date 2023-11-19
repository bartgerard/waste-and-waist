package be.ww.store.query.repository;

import be.ww.shared.type.Amount;
import be.ww.shared.type.ingredient.Quantity;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

public record QuantityField(

        @Field(name = "amount", type = FieldType.Text, norms = false, index = false)
        BigDecimal amount,
        @Field(name = "unit", type = FieldType.Keyword, norms = false, index = false)
        Quantity.Unit unit
) {
    public static QuantityField from(
            final Quantity quantity
    ) {
        return new QuantityField(
                quantity.amount().value(),
                quantity.unit()
        );
    }

    public Quantity toQuantity() {
        return new Quantity(
                Amount.of(amount),
                unit
        );
    }
}
