package currency.pick.kg.jobs;

import currency.pick.kg.services.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class CurrencyRateRetrieveJob {

    private final ExchangeRateService exchangeRateService;

//    @Scheduled(cron = "${currency.job.schedule-time}")
//    public void runJob() {
//        List<ExchangeRateModel> optimalExchangeRateModels = exchangeRateService.getOptimalRates(CurrencyType.KGS);
//    }

}
