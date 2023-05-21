package currency.pick.kg.services;

import currency.pick.kg.models.Currency;
import currency.pick.kg.models.ExchangeRate;

import java.util.List;

public interface CurrencyAnalyzerService {

    void analyze(List<ExchangeRate> optimalExchangeRates);
}
