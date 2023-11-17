package be.ww.shared.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import spock.lang.Specification
import spock.lang.Title

import java.time.LocalDate
import java.time.LocalDateTime

import static java.util.Collections.emptyList
import static org.assertj.core.api.Assertions.assertThat

@Title("ObjectMapperSpecification")
class ObjectMapperSpecification extends Specification {

    private static final ObjectMapperConfiguration OBJECT_MAPPER_CONFIGURATION = new ObjectMapperConfiguration()

    private static final ObjectMapper OBJECT_MAPPER = OBJECT_MAPPER_CONFIGURATION.objectMapper(emptyList())
            .addMixIn(ExampleValueRecord, ExampleValueRecordMixin)
            .addMixIn(ExampleTimeRecord, ExampleTimeRecordMixin)
            .addMixIn(ExampleValueObject, ExampleValueObjectMixin)

    def "serialize / deserialize"() {

        when:
        final Object value = OBJECT_MAPPER.readValue(json, clazz)
        final String valueAsString = OBJECT_MAPPER.writeValueAsString(value)

        then:
        assertThat(value).usingRecursiveComparison().isEqualTo(expectedValue)
        assertThat(valueAsString).isEqualToIgnoringWhitespace(json)

        where:
        json                                        | clazz                              | expectedValue
        "\"2001-01-01\""                            | LocalDate                          | LocalDate.parse("2001-01-01")
        "\"2001-01-01T00:00:00\""                   | LocalDateTime                      | LocalDateTime.parse("2001-01-01T00:00:00")
        "\"test\""                                  | AnnotatedExampleValueRecord        | new AnnotatedExampleValueRecord("test")
        "\"test\""                                  | ExampleValueRecord                 | new ExampleValueRecord("test")
        "\"2001-01-01T00:00:00\""                   | ExampleTimeRecord                  | new ExampleTimeRecord(LocalDateTime.parse("2001-01-01T00:00:00"))
        """{"value" : "test"}"""                    | NoMixinExampleValueRecord          | new NoMixinExampleValueRecord("test")
        """{"value" : "test"}"""                    | ExampleValueObject                 | new ExampleValueObject("test")
        """{"value1" : "test"}"""                   | ExampleOfMultipleConstructorRecord | new ExampleOfMultipleConstructorRecord("test", null)
        """{"value1" : "test", "value2": "test"}""" | ExampleOfMultipleConstructorRecord | new ExampleOfMultipleConstructorRecord("test", "test")

    }

}
