package be.ww.household.query.repository;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Set;

@Document(indexName = "ww-household-query-household")
@Value
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HouseHoldDocument {
    @Id
    String id;

    @EqualsAndHashCode.Include
    @Field(name = "house_hold_id", type = FieldType.Keyword, norms = false)
    String houseHoldId;

    @Field(name = "house_hold_name", type = FieldType.Keyword, norms = false, index = false)
    String houseHoldName;

    @Singular
    @Field(name = "members", type = FieldType.Nested)
    Set<MemberField> members;
}
