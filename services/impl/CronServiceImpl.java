package currency.pick.kg.services.impl;

import currency.pick.kg.converters.CronEntityModelConverter;
import currency.pick.kg.entities.CronEntity;
import currency.pick.kg.exceptions.ExchangeRateException;
import currency.pick.kg.models.CronModel;
import currency.pick.kg.repositories.CronRepository;
import currency.pick.kg.services.CronService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CronServiceImpl implements CronService {

    private final CronRepository cronRepository;

    private final CronEntityModelConverter converter;

    @Override
    public List<CronModel> getAllActiveCrons() {
        List<CronEntity> activeCrons = cronRepository.getAllByIsActiveTrue();
        return activeCrons.stream().map(converter::convert).toList();
    }

    @Override
    public CronModel findById(UUID cronId) {
        Optional<CronEntity> cronEntity = cronRepository.findById(cronId);
        if (cronEntity.isEmpty()) {
            throw new ExchangeRateException(String.format("Cron with id :%s is not found!", cronId));
        }
        return converter.convert(cronEntity.get());
    }

    @Override
    public void deleteById(UUID cronId) {
        try {
            cronRepository.deleteById(cronId);
        } catch (Exception e) {
            log.warn("CronServiceImpl::deleteById, Could not delete cron with id {}", cronId);
            e.printStackTrace();
            throw new ExchangeRateException(String.format("Could not delete cron with id %s", cronId));
        }
    }

    @Override
    public CronModel create(CronModel cronModel) {
        CronEntity cronEntity = converter.convert(cronModel);
        CronEntity cronSaved = cronRepository.save(cronEntity);
        return converter.convert(cronSaved);
    }

    @Override
    public CronModel update(CronModel cronModel) {

        Optional<CronEntity> cronEntity = cronRepository.findById(cronModel.getId());

        if (cronEntity.isEmpty()) {
            throw new ExchangeRateException(String.format("Cron with id :%s is not found!", cronModel.getId()));
        }

        CronEntity cronEntityFromDb = cronEntity.get();

        cronEntityFromDb.setName(cronModel.getName());
        cronEntityFromDb.setCron(cronModel.getCron());
        cronEntityFromDb.setDescription(cronModel.getDescription());
        cronEntityFromDb.setIsActive(cronModel.getIsActive());

        CronEntity cronUpdated = cronRepository.save(cronEntityFromDb);
        return converter.convert(cronUpdated);
    }
}
