package currency.pick.kg.rest;

import currency.pick.kg.enums.CurrencyClientType;
import currency.pick.kg.enums.CurrencyType;
import currency.pick.kg.models.ExchangeRate;

import java.util.List;

public interface CurrencyRestClient {

    List<ExchangeRate> getRates(CurrencyType currencyType);

    CurrencyClientType getCurrencyClientType();
}
