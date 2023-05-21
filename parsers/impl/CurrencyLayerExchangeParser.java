package currency.pick.kg.parsers.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import currency.pick.kg.enums.CurrencyType;
import currency.pick.kg.enums.ExchangeParserType;
import currency.pick.kg.models.ExchangeRate;
import currency.pick.kg.parsers.ExchangeRateParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyLayerExchangeParser implements ExchangeRateParser {

    private final ObjectMapper objectMapper;

    @Value("${currency.allowed}")
    private String [] allowedCurrencies;

    @Override
    public List<ExchangeRate> parse(String jsonResponse) throws JsonProcessingException {

        JsonNode jsonNode = objectMapper.readTree(jsonResponse);

        JsonNode ratesNode = jsonNode.get("rates");
        String ratesJson = objectMapper.writeValueAsString(ratesNode);

        Map<String, BigDecimal> rateMap = objectMapper.readValue(ratesJson, new TypeReference<Map<String, BigDecimal>>() {});
        List<ExchangeRate> exchangeRates = new ArrayList<>();

        for (Map.Entry<String, BigDecimal> entry : rateMap.entrySet()) {
            if (Arrays.stream(allowedCurrencies).anyMatch(key -> key.equals(entry.getKey()))) {
                String currency = entry.getKey();
                BigDecimal rate = entry.getValue();

                ExchangeRate exchangeRate = new ExchangeRate();
                exchangeRate.setExchangeName(getExchangeType().getDescription());
                exchangeRate.setCurrencyType(CurrencyType.valueOf(currency));
                exchangeRate.setRate(rate);
                exchangeRates.add(exchangeRate);
            }
        }

        return exchangeRates;
    }

    @Override
    public ExchangeParserType getExchangeType() {
        return ExchangeParserType.CURRENCY_LAYER;
    }
}
