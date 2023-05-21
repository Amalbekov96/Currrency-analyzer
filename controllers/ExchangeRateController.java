package currency.pick.kg.controllers;

import currency.pick.kg.enums.CurrencyType;
import currency.pick.kg.models.ExchangeRateModel;
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


    @GetMapping("/")
    public ModelAndView view() {

        List<ExchangeRateModel> exchangeRateModelList = exchangeRateService.getOptimalRates(CurrencyType.KGS);
        ModelAndView modelAndView = new ModelAndView("ExchangeRateList");
        modelAndView.addObject("rates", exchangeRateModelList);
        return modelAndView;
    }
}
