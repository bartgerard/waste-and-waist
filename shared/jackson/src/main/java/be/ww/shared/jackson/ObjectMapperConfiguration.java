package be.ww.shared.jackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ObjectMapperConfiguration {

    @Bean
    public ObjectMapper objectMapper(List<Module> modules) {
        return JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .addModule(new ParameterNamesModule())
                .addModule(new Jdk8Module())
                .addModules(modules)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .visibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE)
                .visibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                .enable(SerializationFeature.INDENT_OUTPUT)
                .enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN)
                .enable(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .build();
    }

}