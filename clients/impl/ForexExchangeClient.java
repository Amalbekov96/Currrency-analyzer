package currency.pick.kg.clients.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import currency.pick.kg.clients.CurrencyRestClient;
import currency.pick.kg.enums.CurrencyType;
import currency.pick.kg.enums.ExchangeClientType;
import currency.pick.kg.exceptions.ExchangeRateException;
import currency.pick.kg.factories.ExchangeParserFactory;
import currency.pick.kg.models.ExchangeRateModel;
import currency.pick.kg.parsers.ExchangeRateParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ForexExchangeClient implements CurrencyRestClient {

    private final RestTemplate restTemplate;

    private final ExchangeParserFactory exchangeParserFactory;

    @Value("${exchange.target-currency}")
    private String targetCurrency;

    @Value("${exchange.forex.url}")
    private String url;

    @Value("${currency.allowed}")
    private String [] allowedCurrencies;

    @Override
    public List<ExchangeRateModel> getRates(CurrencyType currencyType) {

        String toCurrencyParam = String.join(",", allowedCurrencies);

        url = url + "&from=" + targetCurrency + "&to=" + toCurrencyParam;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        List<ExchangeRateModel> exchangeRateModels;
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();

            try {
                ExchangeRateParser exchangeRateParser = exchangeParserFactory.getExchangeParserByType(getExchangeClientType());
                exchangeRateModels = exchangeRateParser.parse(responseBody);
            } catch (JsonProcessingException e) {
                log.warn("OpenExchangeClient:getRates - an error occurred while parsing the response, due {}", e.getMessage());
                e.printStackTrace();
                throw new ExchangeRateException("OpenExchangeClient:getRates - an error occurred while parsing the response!");
            }

        } else {
            throw new ExchangeRateException(String.format("Could not retrieve rate info for currency %s , due to response status is %s", targetCurrency, response.getStatusCode()));
        }

        return exchangeRateModels;
    }

    @Override
    public ExchangeClientType getExchangeClientType() {
        return ExchangeClientType.FOREX_EXCHANGE;
    }
}
