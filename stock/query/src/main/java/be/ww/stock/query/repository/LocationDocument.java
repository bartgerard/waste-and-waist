package be.ww.stock.query.repository;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.Value;

@Document(indexName = "ww-stock-command")
@Value
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LocationDocument {
	@Id
	String id;

	@EqualsAndHashCode.Include
	@Field(name = "location_id", type = FieldType.Keyword, norms = false)
	String locationId;
	@Singular
	@Field(name = "appliances", type = FieldType.Nested)
	Set<ApplianceField> appliances;

	@Singular
	@Field(name = "StorageFacilities", type = FieldType.Nested)
	Set<StorageFacilityField> storageFacilities;
}