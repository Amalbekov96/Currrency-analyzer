package currency.pick.kg.controllers;

import currency.pick.kg.models.CronModel;
import currency.pick.kg.services.CronService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cron")
@RequiredArgsConstructor
public class CronController {

    private final CronService cronService;

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("cronPage");
        modelAndView.addObject("cron", new CronModel());
        return modelAndView;
    }



    @DeleteMapping("/delete/{cronId}")
    @ResponseBody
    public ResponseEntity<Boolean> deleteCron(@PathVariable UUID cronId) {
        cronService.deleteById(cronId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(true);
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<CronModel> editCron(@RequestBody CronModel cronModel) {
        CronModel cronModelSaved = cronService.create(cronModel);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cronModelSaved);
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<CronModel> updateCron(@RequestBody CronModel cronModel) {
        CronModel cronModelSaved = cronService.update(cronModel);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cronModelSaved);
    }

    @GetMapping("/crons")
    public ModelAndView getCrons() {

        List<CronModel> allActiveCrons = cronService.getAllActiveCrons();
        ModelAndView modelAndView = new ModelAndView("cronList");
        modelAndView.addObject("crons", allActiveCrons);
        return modelAndView;
    }
}
