package currency.pick.kg.services.impl;

import currency.pick.kg.enums.CurrencyType;
import currency.pick.kg.models.ExchangeRate;
import currency.pick.kg.rest.CurrencyRestClient;
import currency.pick.kg.services.CurrencyAnalyzerService;
import currency.pick.kg.services.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final List<CurrencyRestClient> currencyRestClients;

    private final CurrencyAnalyzerService currencyAnalyzerService;

    @Override
    public List<ExchangeRate> getOptimalRates(CurrencyType currencyType) {

        List<ExchangeRate> optimalExchangeRates = new ArrayList<>();

        for (CurrencyRestClient currencyRestClient : currencyRestClients) {
            List<ExchangeRate> exchangeRates = currencyRestClient.getRates(CurrencyType.KGS);
            if (exchangeRates.isEmpty()) {
                log.warn("Currency rates for {} type is empty from an exchange {}", currencyType, currencyRestClient.getCurrencyClientType());
                continue;
            }

            optimalExchangeRates.addAll(exchangeRates);
        }
        currencyAnalyzerService.analyze(optimalExchangeRates);
        return optimalExchangeRates;
    }
}