package currency.pick.kg.services;

import currency.pick.kg.models.ExchangeRateModel;

import java.util.List;

public interface CurrencyAnalyzerService {

    List<ExchangeRateModel> analyze(List<ExchangeRateModel> optimalExchangeRateModels);
}
