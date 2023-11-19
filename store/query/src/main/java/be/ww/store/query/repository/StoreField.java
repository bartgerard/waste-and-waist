package be.ww.store.query.repository;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Collection;
import java.util.Set;

import static java.util.stream.Collectors.toUnmodifiableSet;

public record StoreField(
        @Field(name = "name", type = FieldType.Keyword, norms = false)
        String name
) {
    public static StoreField from(
            final String store
    ) {
        return new StoreField(store);
    }

    public static Set<StoreField> from(
            final Collection<String> stores
    ) {
        return stores.stream()
                .map(StoreField::from)
                .collect(toUnmodifiableSet());
    }

    public static Set<String> toStores(
            final Collection<StoreField> stores
    ) {
        return stores.stream()
                .map(StoreField::asStore)
                .collect(toUnmodifiableSet());
    }

    public String asStore() {
        return name();
    }
}
