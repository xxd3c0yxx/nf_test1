package ru.xxd3c0yxx.nf_test_1;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Component
public class CalculatorImpl implements ICalculator {
    private static final int daysInYearNum = 366;
    private static final ArrayList<LocalDate> holidaysList;
    static {
        holidaysList = new ArrayList<>();
        holidaysList.add(LocalDate.of(2024,1,1));
        holidaysList.add(LocalDate.of(2024,1,2));
        holidaysList.add(LocalDate.of(2024,1,3));
        holidaysList.add(LocalDate.of(2024,1,4));
        holidaysList.add(LocalDate.of(2024,1,5));
        holidaysList.add(LocalDate.of(2024,1,6));
        holidaysList.add(LocalDate.of(2024,1,7));
        holidaysList.add(LocalDate.of(2024,1,8));
        holidaysList.add(LocalDate.of(2024,2,23));
        holidaysList.add(LocalDate.of(2024,3,8));
        holidaysList.add(LocalDate.of(2024,4,29));
        holidaysList.add(LocalDate.of(2024,4,30));
        holidaysList.add(LocalDate.of(2024,5,1));
        holidaysList.add(LocalDate.of(2024,5,9));
        holidaysList.add(LocalDate.of(2024,5,10));
        holidaysList.add(LocalDate.of(2024,6,12));
        holidaysList.add(LocalDate.of(2024,11,4));
        holidaysList.add(LocalDate.of(2024,12,30));
        holidaysList.add(LocalDate.of(2024,12,31));
    }
    @Override
    public VacPayment calculateVacationPay(BigDecimal wage, long vacationDays) {
        BigDecimal vacationPayment, dailyWage;
        int workDays;

        workDays = daysInYearNum - holidaysList.size();
        dailyWage = wage.divide(BigDecimal.valueOf(workDays),2, RoundingMode.HALF_UP);
        vacationPayment = dailyWage.multiply(BigDecimal.valueOf(vacationDays));
        return new VacPayment(vacationPayment);
    }
    @Override
    public long vacationLenCalc(Date vacationStart, int vacationDays) {
        long vacTotalDaysRange;
        LocalDate vacEnd, vacStart;
        Calendar calendarVE = Calendar.getInstance();

        vacTotalDaysRange = vacationDays;
        calendarVE.setTime(vacationStart);
        calendarVE.add(Calendar.DATE,vacationDays);
        vacEnd = calendarVE.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        vacStart = vacationStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        for (LocalDate day : holidaysList)
            if (day.isAfter(vacStart) && day.isBefore(vacEnd)) {
                vacTotalDaysRange--;
            }
        return (vacTotalDaysRange>0) ? vacTotalDaysRange : 0;
    }
}
