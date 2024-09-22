package ru.xxd3c0yxx.nf_test_1;

import java.math.BigDecimal;

public class VacPayment
{
    private BigDecimal vacationPayment;

    public VacPayment(BigDecimal vacationPayment) {
        this.vacationPayment = vacationPayment;
    }
    public VacPayment()
    {
    }
    public BigDecimal getVacationPayment() {
        return vacationPayment;
    }
    public void setVacationPayment(BigDecimal vacationPayment) {
        this.vacationPayment = vacationPayment;
    }
}
