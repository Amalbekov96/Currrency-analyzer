package currency.pick.kg.models;

import currency.pick.kg.enums.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateModel extends BaseModel {

    private UUID id;
    private String exchangeName;
    private CurrencyType currencyType;
    private BigDecimal rate;
}
