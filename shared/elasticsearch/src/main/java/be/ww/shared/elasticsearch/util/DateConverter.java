package be.ww.shared.elasticsearch.util;

import org.springframework.data.elasticsearch.core.mapping.PropertyValueConverter;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

public class DateConverter implements PropertyValueConverter {

    private static final LocalDate MIN_DATE = LocalDate.of(0, 1, 1);
    private static final LocalDate MAX_DATE = LocalDate.of(9999, 12, 31);

    @NonNull
    @Override
    public Object write(
            @NonNull final Object value
    ) {
        final LocalDate date = (LocalDate) value;
        if (date.isBefore(MIN_DATE)) {
            return MIN_DATE.toString();
        } else if (date.isAfter(MAX_DATE)) {
            return MAX_DATE.toString();
        } else {
            return date.toString();
        }
    }

    @NonNull
    @Override
    public Object read(
            @NonNull final Object value
    ) {
        final LocalDate date = LocalDate.parse((String) value);
        if (date.isEqual(MIN_DATE)) {
            return LocalDate.MIN;
        } else if (date.isEqual(MAX_DATE)) {
            return LocalDate.MAX;
        } else {
            return date;
        }
    }
}
