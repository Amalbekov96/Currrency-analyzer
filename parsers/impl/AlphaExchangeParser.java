package currency.pick.kg.parsers.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import currency.pick.kg.enums.CurrencyType;
import currency.pick.kg.enums.ExchangeClientType;
import currency.pick.kg.models.AlphaExchangeModel;
import currency.pick.kg.models.ExchangeRateModel;
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
public class AlphaExchangeParser implements ExchangeRateParser {

    private final ObjectMapper objectMapper;
    @Override
    public List<ExchangeRateModel> parse(String jsonResponse) throws JsonProcessingException {

        JsonNode jsonNode = objectMapper.readTree(jsonResponse);

        JsonNode ratesNode = jsonNode.get("Realtime Currency Exchange Rate");
        String rateJson = objectMapper.writeValueAsString(ratesNode);

        AlphaExchangeModel alphaExchangeModel = objectMapper.readValue(rateJson, AlphaExchangeModel.class);

        if (alphaExchangeModel == null) {
            log.warn("AlphaExchangeParser:parse: Could not gate the rate for the currency");
            throw new RuntimeException("AlphaExchangeParser:parse: Could not gate the rate for the currency");
        }
        List<ExchangeRateModel> exchangeRateModels = new ArrayList<>();

        ExchangeRateModel exchangeRateModel = new ExchangeRateModel();
        exchangeRateModel.setExchangeName(getExchangeClientType().getDescription());
        exchangeRateModel.setCurrencyType(CurrencyType.valueOf(alphaExchangeModel.getToCurrencyCode()));
        exchangeRateModel.setRate(alphaExchangeModel.getExchangeRate());
        exchangeRateModels.add(exchangeRateModel);

        return exchangeRateModels;
    }

    @Override
    public ExchangeClientType getExchangeClientType() {
        return ExchangeClientType.ALPHA_EXCHANGE;
    }
}
