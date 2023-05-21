package currency.pick.kg.entities;

import currency.pick.kg.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "EXCHANGE_RATE")
public class ExchangeRateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCHANGE_RATE_SEQ")
    @SequenceGenerator(name = "EXCHANGE_RATE_SEQ", sequenceName = "EXCHANGE_RATE_SEQ")
    private Long id;
    private String exchangeName;
    private CurrencyType currencyType;
    private BigDecimal rate;
}
