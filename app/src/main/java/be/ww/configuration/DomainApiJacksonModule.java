package be.ww.configuration;

import be.ww.shared.domain.api.AggregateId;
import be.ww.shared.domain.api.EntityId;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class DomainApiJacksonModule extends SimpleModule {
    public DomainApiJacksonModule() {
        setMixInAnnotation(AggregateId.class, IdMixin.class);
        setMixInAnnotation(EntityId.class, IdMixin.class);
    }
}
