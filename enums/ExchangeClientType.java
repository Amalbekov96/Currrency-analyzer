package currency.pick.kg.enums;

import lombok.Getter;

public enum ExchangeClientType {
    OPEN_EXCHANGE("Open exchange"),
    CURRENCY_LAYER("Currency Layer exchange"),
    ALPHA_EXCHANGE("Kyrgyz nation exchange");

    @Getter
    private final String description;

    ExchangeClientType(String description) {
        this.description = description;
    }
}
