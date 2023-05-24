package currency.pick.kg.enums;

import lombok.Getter;

public enum ExchangeClientType {
    FOREX_EXCHANGE("Forex exchange"),
    CURRENCY_LAYER("Currency Layer exchange"),
    API_EXCHANGE("Api exchange");

    @Getter
    private final String description;

    ExchangeClientType(String description) {
        this.description = description;
    }
}
