package currency.pick.kg.converters;

import currency.pick.kg.entities.CronEntity;
import currency.pick.kg.models.CronModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CronEntityModelConverter implements Converter<CronEntity, CronModel> {

    @Override
    public CronModel convert(CronEntity entity) {
        CronModel model = new CronModel();

        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setCron(entity.getCron());
        model.setDescription(entity.getDescription());
        model.setIsActive(entity.getIsActive());
        model.setIsActive(entity.getIsActive());
        return model;
    }

    public CronEntity convert(CronModel model) {
        CronEntity entity = new CronEntity();

        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setCron(model.getCron());
        entity.setDescription(model.getDescription());
        entity.setIsActive(model.getIsActive());
        return entity;
    }
}
