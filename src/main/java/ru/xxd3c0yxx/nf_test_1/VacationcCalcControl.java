package ru.xxd3c0yxx.nf_test_1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@RestController
public class VacationcCalcControl {
    private final ICalculator calculator;
    public VacationcCalcControl(ICalculator calculator) {
        this.calculator = calculator;
    }
    @GetMapping("/calculate")
    public VacPayment getVacPayment(@RequestParam("avgWage") double avgWage, @RequestParam("vacationDays") int vacationDays, @RequestParam(required = false)String vacationStart) throws ParseException {
        long vacDays;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        if (vacationStart!=null)
            vacDays = calculator.vacationLenCalc(formatter.parse(vacationStart), vacationDays);
        else
            vacDays = vacationDays;
        return calculator.calculateVacationPay(avgWage, vacDays);
    }
}
