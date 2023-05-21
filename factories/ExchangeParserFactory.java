package currency.pick.kg.factories;

import currency.pick.kg.enums.ExchangeClientType;
import currency.pick.kg.parsers.ExchangeRateParser;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ExchangeParserFactory {

    private final List<ExchangeRateParser> exchangeRateParsers;
    private final Map<ExchangeClientType, ExchangeRateParser> currencyRestClientMap = new HashMap<>();

    @PostConstruct
    public void init() {
        for (ExchangeRateParser exchangeRateParser : exchangeRateParsers) {
            if (!currencyRestClientMap.containsKey(exchangeRateParser.getExchangeClientType())) {
                currencyRestClientMap.put(exchangeRateParser.getExchangeClientType(), exchangeRateParser);
            }
        }
    }

    public ExchangeRateParser getExchangeParserByType(ExchangeClientType exchangeClientType) {
        ExchangeRateParser exchangeRateParser = currencyRestClientMap.get(exchangeClientType);
        if (exchangeRateParser == null) {
            throw new RuntimeException(String.format("ExchangeParserFactory: getExchangeParserByType: parser with type: %s is not found.", exchangeClientType));
        }
        return exchangeRateParser;
    }
}

