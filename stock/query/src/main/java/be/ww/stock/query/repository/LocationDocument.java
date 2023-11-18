package be.ww.stock.query.repository;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.ValueConverter;

import be.ww.shared.elasticsearch.util.DateConverter;
import be.ww.shared.type.ingredient.Quantity;
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
	@EqualsAndHashCode.Include
	@Field(name = "product_id", type = FieldType.Keyword, norms = false)
	String productId;
	@EqualsAndHashCode.Include
	@Field(name = "provision_id", type = FieldType.Keyword, norms = false)
	String provisionId;
	@EqualsAndHashCode.Include
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