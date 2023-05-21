package currency.pick.kg.services.impl;

import currency.pick.kg.models.ExchangeRateModel;
import currency.pick.kg.services.CurrencyAnalyzerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyAnalyzerServiceImpl implements CurrencyAnalyzerService {

    @Override
    public List<ExchangeRateModel> analyze(List<ExchangeRateModel> optimalExchangeRateModels) {

        return optimalExchangeRateModels.stream()
                .collect(Collectors.toMap(
                        ExchangeRateModel::getCurrencyType,  // Key: currencyType
                        obj -> obj,  // Value: object itself
                        (obj1, obj2) -> obj1.getRate().compareTo(obj2.getRate()) >= 0 ? obj1 : obj2
                ))
                .values()
                .stream()
                .toList();
    }
}
