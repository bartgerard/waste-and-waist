package be.ww.kitchen.query.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Document(indexName = "ww-kitchen-query-kitchen")
@Value
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class KitchenDocument {
	@Id
	String recipeId;

	@EqualsAndHashCode.Include
	@Field(name = "name", type = FieldType.Keyword, norms = false)
	String name;

}
