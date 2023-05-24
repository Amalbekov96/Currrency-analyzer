package currency.pick.kg.services.impl;

import currency.pick.kg.converters.ExchangeRateEntityModelConverter;
import currency.pick.kg.entities.ExchangeRateEntity;
import currency.pick.kg.enums.CurrencyType;
import currency.pick.kg.mails.MailSenderService;
import currency.pick.kg.models.ExchangeRateModel;
import currency.pick.kg.clients.CurrencyRestClient;
import currency.pick.kg.repositories.ExchangeRateRepository;
import currency.pick.kg.services.CurrencyAnalyzerService;
import currency.pick.kg.services.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final List<CurrencyRestClient> currencyRestClients;

    private final CurrencyAnalyzerService currencyAnalyzerService;

    private final MailSenderService mailSenderService;

    private final ExchangeRateRepository exchangeRateRepository;

    private final ExchangeRateEntityModelConverter converter;

    @Override
    public List<ExchangeRateModel> getOptimalRates(CurrencyType currencyType) {

        List<ExchangeRateModel> optimalExchangeRateModels = new ArrayList<>();

        for (CurrencyRestClient currencyRestClient : currencyRestClients) {
            List<ExchangeRateModel> exchangeRateModels = currencyRestClient.getRates(CurrencyType.KGS);
            if (exchangeRateModels.isEmpty()) {
                log.warn("Currency rates for {} type is empty from an exchange {}", currencyType, currencyRestClient.getExchangeClientType());
                continue;
            }

            optimalExchangeRateModels.addAll(exchangeRateModels);
        }
        List<ExchangeRateModel> exchangeRateModels = currencyAnalyzerService.analyze(optimalExchangeRateModels);

        exchangeRateRepository.saveAll(exchangeRateModels.stream().map(converter::convert).toList());

        mailSenderService.sendEmail("Amalbekov312@gmail.com", "Optimal rate", exchangeRateModels.toString());

        return exchangeRateModels;
    }

    @Override
    public void saveAll(List<ExchangeRateModel> optimalExchangeRateModels) {
        exchangeRateRepository.saveAll(optimalExchangeRateModels.stream().map(converter::convert).toList());
    }
}