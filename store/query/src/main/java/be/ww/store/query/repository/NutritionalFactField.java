package be.ww.store.query.repository;

import be.ww.shared.type.Amount;
import be.ww.shared.type.ingredient.NutritionalFact;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

public record NutritionalFactField(
        @Field(name = "fact", type = FieldType.Keyword, norms = false, index = false)
        NutritionalFact.Fact fact,
        @Field(name = "amount", type = FieldType.Keyword, norms = false, index = false)
        BigDecimal amount
) {
    public static NutritionalFactField from(
            final NutritionalFact fact
    ) {
        return new NutritionalFactField(
                fact.fact(),
                fact.amount().value()
        );
    }

    public NutritionalFact asFact() {
        return new NutritionalFact(
                fact,
                Amount.of(amount)
        );
    }
}
