package currency.pick.kg.clients;

import currency.pick.kg.enums.CurrencyType;
import currency.pick.kg.enums.ExchangeClientType;
import currency.pick.kg.models.ExchangeRateModel;

import java.util.List;

public interface CurrencyRestClient {

    List<ExchangeRateModel> getRates(CurrencyType currencyType);

    ExchangeClientType getExchangeClientType();
}
