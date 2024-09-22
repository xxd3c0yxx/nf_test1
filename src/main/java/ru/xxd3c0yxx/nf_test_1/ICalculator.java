package ru.xxd3c0yxx.nf_test_1;

import java.math.BigDecimal;
import java.util.Date;

public interface ICalculator {
    VacPayment calculateVacationPay(BigDecimal wage, long vacationDays);
    long vacationLenCalc(Date vacationStart, int vacationDays);
}
