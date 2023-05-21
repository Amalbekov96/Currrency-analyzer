package currency.pick.kg.enums;

import lombok.Getter;

public enum ExchangeParserType {
    OPEN_EXCHANGE("Open exchange"),
    CURRENCY_LAYER("Currency Layer exchange");

    @Getter
    private final String description;

    ExchangeParserType(String description) {
        this.description = description;
    }
}
