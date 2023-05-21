package currency.pick.kg.clients.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import currency.pick.kg.clients.CurrencyRestClient;
import currency.pick.kg.enums.CurrencyType;
import currency.pick.kg.enums.ExchangeClientType;
import currency.pick.kg.factories.ExchangeParserFactory;
import currency.pick.kg.models.ExchangeRateModel;
import currency.pick.kg.parsers.ExchangeRateParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlphaExchangeClient implements CurrencyRestClient {

    private final RestTemplate restTemplate;

    private final ExchangeParserFactory exchangeParserFactory;

    @Value("${exchange.target-currency}")
    private String targetCurrency;

    @Value("${exchange.alpha.url}")
    private String url;

    @Value("${exchange.alpha.key}")
    private String key;

    @Value("${currency.allowed}")
    private String [] allowedCurrencies;

    @Override
    public List<ExchangeRateModel> getRates(CurrencyType currencyType) {

        String function = "CURRENCY_EXCHANGE_RATE";

        String toCurrencyParam = String.join(",", allowedCurrencies);
        List<ExchangeRateModel> exchangeRateModels = new ArrayList<>();

        for (String toCurrency : allowedCurrencies) {
            String requestUrl = url + "?function=" + function + "&from_currency=" + targetCurrency + "&to_currency=" + toCurrency + "&apikey=" + key;
            ResponseEntity<String> response = restTemplate.getForEntity(requestUrl, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                String responseBody = response.getBody();
                List<ExchangeRateModel> parsedExchangeRates;
                try {
                    ExchangeRateParser exchangeRateParser = exchangeParserFactory.getExchangeParserByType(getExchangeClientType());
                    parsedExchangeRates = exchangeRateParser.parse(responseBody);
                } catch (JsonProcessingException e) {
                    log.warn("OpenExchangeClient:getRates - an error occurred while parsing the response, due {}", e.getMessage());
                    e.printStackTrace();
                    throw new RuntimeException("OpenExchangeClient:getRates - an error occurred while parsing the response!");
                }

                exchangeRateModels.addAll(parsedExchangeRates);

            } else {
                throw new RuntimeException(String.format("Could not retrieve rate info for currency %s , due to response status is %s", targetCurrency, response.getStatusCode()));
            }
        }

        return exchangeRateModels;
    }

    @Override
    public ExchangeClientType getExchangeClientType() {
        return ExchangeClientType.ALPHA_EXCHANGE;
    }
}
