package currency.pick.kg.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import currency.pick.kg.enums.ExchangeParserType;
import currency.pick.kg.models.ExchangeRate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ExchangeRateParser {
    List<ExchangeRate> parse(String jsonRate) throws JsonProcessingException;

    ExchangeParserType getExchangeType();
}
