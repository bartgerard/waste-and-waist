package be.ww.store.query.repository;

import be.ww.shared.type.ingredient.Allergen;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Collection;
import java.util.Set;

import static java.util.stream.Collectors.toUnmodifiableSet;

public record AllergenField(
        @Field(name = "name", type = FieldType.Keyword, norms = false)
        Allergen allergen
) {
    public static AllergenField from(
            final Allergen allergen
    ) {
        return new AllergenField(allergen);
    }

    public static Set<AllergenField> from(
            final Collection<Allergen> stores
    ) {
        return stores.stream()
                .map(AllergenField::from)
                .collect(toUnmodifiableSet());
    }

    public static Set<Allergen> toAllergens(
            final Collection<AllergenField> stores
    ) {
        return stores.stream()
                .map(AllergenField::asAllergen)
                .collect(toUnmodifiableSet());
    }

    public Allergen asAllergen() {
        return allergen();
    }
}
