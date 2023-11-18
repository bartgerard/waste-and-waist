package be.ww.user.service;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "ww-user")
@Value
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserDocument {
    @Id
    String id;

    @EqualsAndHashCode.Include
    @Field(name = "user_id", type = FieldType.Keyword, norms = false)
    String userId;

    @Field(name = "username", type = FieldType.Keyword, norms = false)
    String username;

    @Field(name = "password", type = FieldType.Keyword, norms = false)
    String password;
}
