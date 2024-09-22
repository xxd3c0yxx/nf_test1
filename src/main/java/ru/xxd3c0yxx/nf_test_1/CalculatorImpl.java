package ru.xxd3c0yxx.nf_test_1;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

@Component
public class CalculatorImpl implements ICalculator {
    private static final int daysInYearNum = 366;
    private static final ArrayList<GregorianCalendar> holidaysList;
    static {
        holidaysList = new ArrayList<>();
        holidaysList.add(new GregorianCalendar(2024, Calendar.JANUARY,1));
        holidaysList.add(new GregorianCalendar(2024,Calendar.JANUARY,2));
        holidaysList.add(new GregorianCalendar(2024,Calendar.JANUARY,3));
        holidaysList.add(new GregorianCalendar(2024,Calendar.JANUARY,4));
        holidaysList.add(new GregorianCalendar(2024,Calendar.JANUARY,5));
        holidaysList.add(new GregorianCalendar(2024,Calendar.JANUARY,6));
        holidaysList.add(new GregorianCalendar(2024,Calendar.JANUARY,7));
        holidaysList.add(new GregorianCalendar(2024,Calendar.JANUARY,8));
        holidaysList.add(new GregorianCalendar(2024,Calendar.FEBRUARY,23));
        holidaysList.add(new GregorianCalendar(2024,Calendar.MARCH,8));
        holidaysList.add(new GregorianCalendar(2024,Calendar.APRIL,29));
        holidaysList.add(new GregorianCalendar(2024,Calendar.APRIL,30));
        holidaysList.add(new GregorianCalendar(2024,Calendar.MAY,1));
        holidaysList.add(new GregorianCalendar(2024,Calendar.MAY,9));
        holidaysList.add(new GregorianCalendar(2024,Calendar.MAY,10));
        holidaysList.add(new GregorianCalendar(2024,Calendar.JUNE,12));
        holidaysList.add(new GregorianCalendar(2024,Calendar.NOVEMBER,4));
        holidaysList.add(new GregorianCalendar(2024,Calendar.DECEMBER,30));
        holidaysList.add(new GregorianCalendar(2024,Calendar.DECEMBER,31));
    }

    public CalculatorImpl() {
    }

    @Override
    public VacPayment calculateVacationPay(double wage, long vacationDays) {
        double vacationPayment;

        vacationPayment = (wage / (daysInYearNum - holidaysList.size())) * vacationDays;
        return new VacPayment(vacationPayment);
    }
    @Override
    public long vacationLenCalc(Date vacationStart, int vacationDays) {
        long vacTotalDaysRange;
        Date vacationEnd;
        Calendar calendarVE = Calendar.getInstance();

        vacTotalDaysRange = TimeUnit.MILLISECONDS.toDays(vacationStart.getTime())+vacationDays;
        calendarVE.setTime(vacationStart);
        calendarVE.add(Calendar.DATE,vacationDays);
        vacationEnd = calendarVE.getTime();

        if (vacTotalDaysRange>0) {
            for (GregorianCalendar day : holidaysList) {
                if (day.after(vacationStart) && day.before(vacationEnd)) {
                    vacTotalDaysRange--;
                }
            }
        }
        if (vacTotalDaysRange>0) {
            return vacTotalDaysRange;
        }
        else{
            return 0;
        }
    }
}
