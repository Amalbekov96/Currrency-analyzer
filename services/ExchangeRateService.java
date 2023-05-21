package currency.pick.kg.services;

import currency.pick.kg.enums.CurrencyType;
import currency.pick.kg.models.ExchangeRateModel;

import java.util.List;

public interface ExchangeRateService {
    List<ExchangeRateModel> getOptimalRates(CurrencyType currencyType);
}
