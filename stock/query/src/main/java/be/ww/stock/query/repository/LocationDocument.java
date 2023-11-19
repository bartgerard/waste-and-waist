package be.ww.stock.query.repository;

import be.ww.shared.elasticsearch.util.DateConverter;
import be.ww.shared.type.ingredient.Quantity;
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

import java.time.LocalDate;
import java.util.Set;

@Document(indexName = "ww-stock-query-location")
@Value
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LocationDocument {
    @Id
    String id;

    @EqualsAndHashCode.Include
    @Field(name = "location_id", type = FieldType.Keyword, norms = false)
    String locationId;

    @Field(name = "house_hold_id", type = FieldType.Keyword, norms = false)
    String houseHoldId;

    @Field(name = "name", type = FieldType.Keyword, norms = false)
    String name;

    @Singular
    @Field(name = "appliances", type = FieldType.Nested)
    Set<ApplianceField> appliances;

    @Singular
    @Field(name = "storage_facilities", type = FieldType.Nested)
    Set<StorageFacilityField> storageFacilities;

    @Field(name = "product_id", type = FieldType.Keyword, norms = false)
    String productId;

    @Field(name = "provision_id", type = FieldType.Keyword, norms = false)
    String provisionId;

    @Field(name = "ingredient_id", type = FieldType.Keyword, norms = false)
    String ingredientId;

    Quantity quantity;

    @Field(name = "best_before", type = FieldType.Date, format = DateFormat.date)
    @ValueConverter(DateConverter.class)
    LocalDate bestBefore;

    @Field(name = "used_by", type = FieldType.Date, format = DateFormat.date)
    @ValueConverter(DateConverter.class)
    LocalDate usedBy;
}