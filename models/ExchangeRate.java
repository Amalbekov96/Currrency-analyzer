package currency.pick.kg.models;

import currency.pick.kg.enums.CurrencyType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExchangeRate {

    private String exchangeName;
    private CurrencyType currencyType;
    private BigDecimal rate;
}
