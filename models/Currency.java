package currency.pick.kg.models;

import currency.pick.kg.enums.CurrencyType;
import lombok.Data;

@Data
public class Currency {

    private String name;
    private CurrencyType currencyType;
}
