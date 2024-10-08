package ru.xxd3c0yxx.nf_test_1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@RestController
public class VacationcCalcControl {
    private final ICalculator calculator;
    public VacationcCalcControl(ICalculator calculator) {
        this.calculator = calculator;
    }
    @GetMapping("/calculate")
    public VacPayment getVacPayment(@RequestParam("avgWage") BigDecimal avgWage, @RequestParam("vacationDays") int vacationDays, @RequestParam(required = false)String vacationStart) throws ParseException {
        long vacDays;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH); // eg. 01-Jun-2024
        if (vacationStart!=null) //if additional parameter 'start date of the vacation' is used
            vacDays = calculator.vacationLenCalc(formatter.parse(vacationStart), vacationDays);
        else
            vacDays = vacationDays;
        return calculator.calculateVacationPay(avgWage, vacDays);
    }
}
