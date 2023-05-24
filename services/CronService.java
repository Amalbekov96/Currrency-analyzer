package currency.pick.kg.services;

import currency.pick.kg.models.CronModel;

import java.util.List;
import java.util.UUID;

public interface CronService {

    List<CronModel> getAllActiveCrons();

    CronModel findById(UUID cronId);

    void deleteById(UUID cronId);

    CronModel create(CronModel cronModel);

    CronModel update(CronModel cronModel);
}
