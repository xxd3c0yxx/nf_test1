package ru.xxd3c0yxx.nf_test_1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class VacationcCalcControl {
    private final ICalculator calculator;
    public VacationcCalcControl(ICalculator calculator) {
        this.calculator = calculator;
    }
    @GetMapping("/calculate")
    public VacPayment getVacPayment(@RequestParam("avgWage") double avgWage, @RequestParam("vacationDays") int vacationDays, @RequestParam(required = false)Date vacationStart){
        long vacDays;
        if (vacationStart!=null)
            vacDays = calculator.vacationLenCalc(vacationStart, vacationDays);
        else
            vacDays = vacationDays;
        return calculator.calculateVacationPay(avgWage, vacDays);
    }
}
