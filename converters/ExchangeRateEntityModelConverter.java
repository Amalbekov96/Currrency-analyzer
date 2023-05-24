package currency.pick.kg.converters;

import currency.pick.kg.entities.ExchangeRateEntity;
import currency.pick.kg.models.ExchangeRateModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRateEntityModelConverter implements Converter<ExchangeRateEntity, ExchangeRateModel> {

    @Override
    public ExchangeRateModel convert(ExchangeRateEntity entity) {
        ExchangeRateModel model = new ExchangeRateModel();

        model.setId(entity.getId());
        model.setExchangeName(entity.getExchangeName());
        model.setCurrencyType(entity.getCurrencyType());
        model.setRate(entity.getRate());
        return model;
    }

    public ExchangeRateEntity convert(ExchangeRateModel model) {
        ExchangeRateEntity entity = new ExchangeRateEntity();

        entity.setId(model.getId());
        entity.setExchangeName(model.getExchangeName());
        entity.setCurrencyType(model.getCurrencyType());
        entity.setRate(model.getRate());
        return entity;
    }
}
