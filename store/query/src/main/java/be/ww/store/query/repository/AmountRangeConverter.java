package be.ww.store.query.repository;

import be.ww.shared.type.ingredient.AmountRange;
import org.springframework.data.elasticsearch.core.mapping.PropertyValueConverter;
import org.springframework.lang.NonNull;

import static org.apache.commons.lang3.Validate.notEmpty;

public class AmountRangeConverter implements PropertyValueConverter {
    @NonNull
    @Override
    public Object write(@NonNull final Object value) {
        final AmountRange range = (AmountRange) value;
        return AmountRange.serialize(range);
    }

    @NonNull
    @Override
    public Object read(@NonNull final Object value) {
        final String rangeAsText = (String) value;
        notEmpty(rangeAsText);
        return AmountRange.parse(rangeAsText);
    }
}
