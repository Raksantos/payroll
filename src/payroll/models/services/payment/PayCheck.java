package models.services.payment;

import java.time.LocalDate;

import models.Employee;

public class PayCheck {
    private Employee employee;

    private Double paymentValue;

    //private Double taxes;

    private boolean haveTax;

    private LocalDate date;

    PayCheck(Employee employee, Double paymentValue, boolean haveTax, LocalDate date){
        this.employee = employee;
        this.paymentValue = paymentValue;
        this.haveTax = haveTax;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Double getPaymentValue() {
        return paymentValue;
    }

    public boolean isHaveTax() {
        return haveTax;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setHaveTax(boolean haveTax) {
        this.haveTax = haveTax;
    }

    public void setPaymentValue(Double paymentValue) {
        this.paymentValue = paymentValue;
    }
}
