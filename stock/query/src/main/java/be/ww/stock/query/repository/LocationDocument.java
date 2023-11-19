package be.ww.stock.query.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import be.ww.stock.api.command.StoreProvisionsCommand;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.Value;

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
    @Field(name = "provision_id", type = FieldType.Keyword, norms = false)
    String provisionId;
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

    @Singular
    @Field(name = "provisions", type = FieldType.Nested)
    List<StoreProvisionsCommand.Product> provisions;

}