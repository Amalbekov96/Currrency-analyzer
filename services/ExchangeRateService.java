package currency.pick.kg.services;

import currency.pick.kg.enums.CurrencyType;
import currency.pick.kg.models.ExchangeRate;

import java.util.List;

public interface ExchangeRateService {
    List<ExchangeRate> getOptimalRates(CurrencyType currencyType);
}
