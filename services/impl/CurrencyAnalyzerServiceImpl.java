package currency.pick.kg.services.impl;

import currency.pick.kg.models.ExchangeRate;
import currency.pick.kg.services.CurrencyAnalyzerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyAnalyzerServiceImpl implements CurrencyAnalyzerService {

    @Override
    public void analyze(List<ExchangeRate> optimalExchangeRates) {
        optimalExchangeRates.sort((rate1, rate2) -> {
            // Compare currencyType
            int currencyTypeComparison = rate1.getCurrencyType().compareTo(rate2.getCurrencyType());

            if (currencyTypeComparison != 0) {
                // If currencyType values are different, return the comparison result
                return currencyTypeComparison;
            } else {
                // If currencyType values are equal, compare by rate
                return rate2.getRate().compareTo(rate1.getRate());
            }
        });

    }
}
