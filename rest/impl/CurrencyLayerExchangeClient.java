package currency.pick.kg.rest.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import currency.pick.kg.enums.CurrencyClientType;
import currency.pick.kg.enums.CurrencyType;
import currency.pick.kg.models.ExchangeRate;
import currency.pick.kg.parsers.impl.CurrencyLayerExchangeParser;
import currency.pick.kg.rest.CurrencyRestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyLayerExchangeClient implements CurrencyRestClient {

    private final RestTemplate restTemplate;

    private final CurrencyLayerExchangeParser currencyLayerExchangeParser;

    @Value("${exchange.api-layer.url}")
    private String url;

    @Value("${exchange.api-layer.key}")
    private String key;

    @Value("${exchange.target-currency}")
    private String targetCurrency;

    @Value("${currency.allowed}")
    private String [] allowedCurrencies;


    @Override
    public List<ExchangeRate> getRates(CurrencyType currencyType) {
        String requestedSymbols = Arrays.toString(allowedCurrencies);
        url = url+targetCurrency+"&symbols=" + requestedSymbols.substring(1, requestedSymbols.length() - 1);

        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", key);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        List<ExchangeRate> exchangeRates;
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();

            try {
                exchangeRates = currencyLayerExchangeParser.parse(responseBody);
            } catch (JsonProcessingException e) {
                log.warn("OpenExchangeClient:getRates - an error occurred while parsing the response, due {}", e.getMessage());
                e.printStackTrace();
                throw new RuntimeException("OpenExchangeClient:getRates - an error occurred while parsing the response!");
            }

        } else {
            throw new RuntimeException(String.format("Could not retrieve rate info for currency %s , due to response status is %s", currencyType.toString(), response.getStatusCode()));
        }

        return exchangeRates;
    }

    @Override
    public CurrencyClientType getCurrencyClientType() {
        return CurrencyClientType.OPEN_EXCHANGE;
    }
}