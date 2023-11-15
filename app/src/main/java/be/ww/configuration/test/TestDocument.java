package be.ww.configuration.test;

import be.ww.shared.elasticsearch.DateConverter;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.ValueConverter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Document(indexName = "ww-stock-query-test")
@Value
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TestDocument {
    @Id
    String id;

    @EqualsAndHashCode.Include
    @Field(name = "test_id", type = FieldType.Keyword, norms = false)
    String testId;

    @Field(name = "day", type = FieldType.Date, format = DateFormat.date)
    @ValueConverter(DateConverter.class)
    LocalDate day;

    @Singular
    @Field(name = "inner_tests", type = FieldType.Nested)
    List<InnerTest> innerTests;

    @Builder
    record InnerTest(
            @Field(name = "test_1", type = FieldType.Keyword, norms = false, index = false)
            String test1,
            @Field(name = "value_1", type = FieldType.Text, norms = false, index = false)
            BigDecimal value1
    ) {
    }
}
