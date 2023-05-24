package currency.pick.kg.jobs;

import currency.pick.kg.enums.CurrencyType;
import currency.pick.kg.models.ExchangeRateModel;
import currency.pick.kg.services.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class CurrencyRateRetrieveJob {

    private final ExchangeRateService exchangeRateService;

    @Scheduled(cron = "${currency.job.schedule-time}")
    public void runJob() {
        try {
            List<ExchangeRateModel> optimalExchangeRateModels = exchangeRateService.getOptimalRates(CurrencyType.KGS);
            exchangeRateService.saveAll(optimalExchangeRateModels);
        } catch (Exception e) {
            log.warn("An error occured while retreiving the optimal rate for currency {}. Error message: {}", CurrencyType.KGS, e.getMessage());
            e.printStackTrace();
        }

    }

}
