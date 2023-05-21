package currency.pick.kg.factory;

import currency.pick.kg.enums.CurrencyClientType;
import currency.pick.kg.rest.CurrencyRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public abstract class CurrencyRestClientFactory {

    private final List<CurrencyRestClient> currencyRestClients;
    private Map<CurrencyClientType, CurrencyRestClient> currencyRestClientMap;

    private void CurrencyRestClientFactory() {
        for (CurrencyRestClient currencyRestClient : this.currencyRestClients) {
            if(!this.currencyRestClientMap.containsKey(currencyRestClient.getCurrencyClientType())) {
                this.currencyRestClientMap.put(currencyRestClient.getCurrencyClientType(), currencyRestClient);
            }
        }
    }


    private CurrencyRestClient getCurrencyClient(CurrencyClientType currencyClientType) {
        CurrencyRestClient currencyRestClient = this.currencyRestClientMap.get(currencyClientType);

        if(currencyRestClient == null) {
            throw new RuntimeException(String.format("CurrencyRestClientFactory:getCurrencyClient: currency client with type: %s is not found.", currencyClientType));
        }
        return currencyRestClient;
    }
}
