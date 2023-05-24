package currency.pick.kg.controllers;

import currency.pick.kg.enums.CurrencyType;
import currency.pick.kg.models.CronModel;
import currency.pick.kg.models.ExchangeRateModel;
import currency.pick.kg.services.CronService;
import currency.pick.kg.services.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/exchange")
@RequiredArgsConstructor
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;
    private final CronService cronService;

    @GetMapping("/")
    public String showIndex(){
        return "index";
    }

    @GetMapping("/rates")
    public ModelAndView getOptimalRates() {

        List<ExchangeRateModel> exchangeRateModelList = exchangeRateService.getOptimalRates(CurrencyType.KGS);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("rates", exchangeRateModelList);
        return modelAndView;
    }
    @GetMapping("/crons")
    public ModelAndView getCrons() {

        List<CronModel> allActiveCrons = cronService.getAllActiveCrons();
        ModelAndView modelAndView = new ModelAndView("cronList");
        modelAndView.addObject("crons", allActiveCrons);
        return modelAndView;
    }
}
