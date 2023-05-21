package currency.pick.kg.factory;

import currency.pick.kg.enums.ExchangeParserType;
import currency.pick.kg.parsers.ExchangeRateParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public abstract class ExchangeParserFactory {

    private final List<ExchangeRateParser> exchangeRateParsers;
    private Map<ExchangeParserType,ExchangeRateParser> currencyRestClientMap;

    private void ExchangeParserFactory() {
        for (ExchangeRateParser exchangeRateParser : this.exchangeRateParsers) {
            if(!this.currencyRestClientMap.containsKey(exchangeRateParser.getExchangeType())) {
                this.currencyRestClientMap.put(exchangeRateParser.getExchangeType(), exchangeRateParser);
            }
        }
    }


    private ExchangeRateParser getExchangeParserByType(ExchangeParserType exchangeParserType) {
        ExchangeRateParser exchangeRateParser = this.currencyRestClientMap.get(exchangeParserType);
        if(exchangeRateParser == null) {
            throw new RuntimeException(String.format("ExchangeParserFactory:getExchangeParserByType: parser with type: %s is not found.", exchangeParserType));
        }
        return exchangeRateParser;
    }
}
