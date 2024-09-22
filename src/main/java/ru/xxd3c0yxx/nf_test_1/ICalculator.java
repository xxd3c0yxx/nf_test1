package ru.xxd3c0yxx.nf_test_1;

import java.util.Date;

public interface ICalculator {
    VacPayment calculateVacationPay(double wage, long vacationDays);
    long vacationLenCalc(Date vacationStart, int vacationDays);
}
