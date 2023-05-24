package currency.pick.kg.entities;

import currency.pick.kg.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "EXCHANGE_RATE")
public class ExchangeRateEntity extends BaseEntity{
    private String exchangeName;
    private CurrencyType currencyType;
    private BigDecimal rate;
}
