package be.ww.configuration.jackson;

import be.ww.shared.type.ingredient.AmountRange;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;

@JsonSerialize(
        using = AmountRangeMixin.AmountRangeSerializer.class
)
@JsonDeserialize(
        using = AmountRangeMixin.AmountRangeDeserializer.class
)
public abstract class AmountRangeMixin {

    public static class AmountRangeSerializer extends JsonSerializer<AmountRange> {
        @Override
        public void serialize(
                final AmountRange range,
                final JsonGenerator jsonGenerator,
                final SerializerProvider serializerProvider
        ) throws IOException {
            jsonGenerator.writeString(AmountRange.serialize(range));
        }
    }

    public static class AmountRangeDeserializer extends JsonDeserializer<AmountRange> {
        @Override
        public AmountRange deserialize(
                final JsonParser jsonParser,
                final DeserializationContext deserializationContext
        ) throws IOException {
            final String s = jsonParser.getValueAsString();
            return AmountRange.parse(s.trim());
        }
    }
}
