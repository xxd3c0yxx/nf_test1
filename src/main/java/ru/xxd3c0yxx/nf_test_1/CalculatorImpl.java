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
    private static final float avgDaysInMonthNum = 29.3F; // avg number of the days in a month
    //private static final int daysInYearNum = 366;
    private static final ArrayList<LocalDate> holidaysList; //the list of holidays for 2024
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
    public VacPayment calculateVacationPay(BigDecimal wage, long vacationDays) { //calculates final vacation payment
        BigDecimal vacationPayment, dailyWage;

        //workDays = daysInYearNum - holidaysList.size(); //commented - using avg monthly wage instead of annual income
        dailyWage = wage.divide(BigDecimal.valueOf(avgDaysInMonthNum),2, RoundingMode.HALF_UP); //calculating daily wage
        vacationPayment = dailyWage.multiply(BigDecimal.valueOf(vacationDays)); //calculating vacation payment based on avg daily wage and number of vacation days
        return new VacPayment(vacationPayment);
    }
    @Override
    public long vacationLenCalc(Date vacationStart, int vacationDays) { //calculates days of vacation considering holidays
        long vacTotalDaysRange;
        LocalDate vacEnd, vacStart;
        Calendar calendarVE = Calendar.getInstance();

        vacTotalDaysRange = vacationDays;
        calendarVE.setTime(vacationStart);
        calendarVE.add(Calendar.DATE,vacationDays);
        vacEnd = calendarVE.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        vacStart = vacationStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        for (LocalDate day : holidaysList) //looping through the list of holidays to check if any of them are occur during vacation
            if (day.isAfter(vacStart) && day.isBefore(vacEnd)) {
                vacTotalDaysRange--;
            }
        return (vacTotalDaysRange>0) ? vacTotalDaysRange : 0;
    }
}
