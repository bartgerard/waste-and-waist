package be.ww.household.query.repository;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Objects;

public record MemberField(

        @Field(name = "name", type = FieldType.Keyword, norms = false)
        String name,
        @Field(name = "user_id", type = FieldType.Keyword, norms = false)
        String userId
) {
    
    public static MemberField of(
            String name,
            String userId
    ) {
        return new MemberField(name, userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberField that = (MemberField) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
