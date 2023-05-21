package currency.pick.kg.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import currency.pick.kg.enums.ExchangeClientType;
import currency.pick.kg.models.ExchangeRateModel;

import java.util.List;


public interface ExchangeRateParser {
    List<ExchangeRateModel> parse(String jsonRate) throws JsonProcessingException;

    ExchangeClientType getExchangeClientType();
}
