package be.ww.configuration;

import be.ww.configuration.jackson.AmountMixin;
import be.ww.configuration.jackson.IdMixin;
import be.ww.shared.domain.api.AggregateId;
import be.ww.shared.domain.api.EntityId;
import be.ww.shared.type.Amount;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class DomainApiJacksonModule extends SimpleModule {
    public DomainApiJacksonModule() {
        setMixInAnnotation(AggregateId.class, IdMixin.class);
        setMixInAnnotation(EntityId.class, IdMixin.class);
        setMixInAnnotation(Amount.class, AmountMixin.class);
    }
}
