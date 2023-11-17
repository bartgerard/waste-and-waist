package be.ww.household.query.repository;

import be.ww.shared.elasticsearch.util.DateConverter;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.ValueConverter;

import java.time.LocalDate;
import java.util.Objects;

public record MemberField(
        @Field(name = "member_id", type = FieldType.Keyword, norms = false)
        String memberId,
        @Field(name = "name", type = FieldType.Keyword, norms = false)
        String name,
        @Field(name = "birth_date", type = FieldType.Date, format = DateFormat.date)
        @ValueConverter(DateConverter.class)
        LocalDate birthDate,
        @Field(name = "user_id", type = FieldType.Keyword, norms = false)
        String userId
) {
    public static MemberField of(
            String memberId,
            String name,
            LocalDate birthDate,
            String userId
    ) {
        return new MemberField(memberId, name, birthDate, userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberField that = (MemberField) o;
        return Objects.equals(memberId, that.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }
}
