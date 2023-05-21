package currency.pick.kg.jobs;

import currency.pick.kg.enums.CurrencyType;
import currency.pick.kg.models.ExchangeRateModel;
import currency.pick.kg.services.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class CurrencyRateRetrieveJob {

    private final ExchangeRateService exchangeRateService;

    @Scheduled(cron = "${currency.job.schedule-time}")
    public void runJob() {
        List<ExchangeRateModel> optimalExchangeRateModels = exchangeRateService.getOptimalRates(CurrencyType.KGS);
    }

}
