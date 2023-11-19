package be.ww.kitchen.query.repository;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import be.ww.kitchen.api.type.Instruction;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.Value;

@Document(indexName = "ww-kitchen-query-kitchen")
@Value
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class KitchenDocument {
	@Id
	String id;

	@EqualsAndHashCode.Include
	@Field(name = "recipe_id", type = FieldType.Keyword, norms = false)
	String recipeId;
	@EqualsAndHashCode.Include
	@Field(name = "name", type = FieldType.Keyword, norms = false)
	String name;

	@Singular
	@Field(name = "instructions", type = FieldType.Nested)
	List<Instruction> instructions;


	@Field(name = "portionSize", type = FieldType.Integer, norms = false)
	int portionSize;

}
