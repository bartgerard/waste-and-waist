package be.ww.stock.command.repository;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "ww-stock-command-household")
@Value
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StockHouseHoldDocument {
    @Id
    String id;

    @EqualsAndHashCode.Include
    @Field(name = "house_hold_id", type = FieldType.Keyword, norms = false)
    String houseHoldId;
}
