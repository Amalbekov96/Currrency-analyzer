package currency.pick.kg.parsers.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import currency.pick.kg.enums.CurrencyType;
import currency.pick.kg.enums.ExchangeClientType;
import currency.pick.kg.models.ExchangeRateModel;
import currency.pick.kg.parsers.ExchangeRateParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ForexExchangeParser implements ExchangeRateParser {

    private final ObjectMapper objectMapper;

    @Override
    public List<ExchangeRateModel> parse(String jsonResponse) throws JsonProcessingException {

        JsonNode jsonNode = objectMapper.readTree(jsonResponse);

        JsonNode ratesNode = jsonNode.get("results");
        String ratesJson = objectMapper.writeValueAsString(ratesNode);

        Map<String, BigDecimal> rateMap = objectMapper.readValue(ratesJson, new TypeReference<>() {});
        List<ExchangeRateModel> exchangeRateModels = new ArrayList<>();

        for (Map.Entry<String, BigDecimal> entry : rateMap.entrySet()) {
            String currency = entry.getKey();
            BigDecimal rate = entry.getValue();

            ExchangeRateModel exchangeRateModel = new ExchangeRateModel();
            exchangeRateModel.setExchangeName(getExchangeClientType().getDescription());
            exchangeRateModel.setCurrencyType(CurrencyType.valueOf(currency));
            exchangeRateModel.setRate(rate);
            exchangeRateModels.add(exchangeRateModel);
        }

        return exchangeRateModels;
    }

    @Override
    public ExchangeClientType getExchangeClientType() {
        return ExchangeClientType.FOREX_EXCHANGE;
    }
}
