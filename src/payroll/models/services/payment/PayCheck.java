package models.services.payment;

import java.io.Serializable;
import java.time.LocalDate;

import models.Employee;

public class PayCheck implements Serializable{
    private Employee employee;

    private Double paymentValue;

    private Double taxes;

    private boolean haveTax;

    private LocalDate date;

    public PayCheck(){
        
    }

    public PayCheck(Employee employee, Double paymentValue, Double taxes, boolean haveTax, LocalDate date){
        this.employee = employee;
        this.paymentValue = paymentValue;
        this.taxes = taxes;
        this.haveTax = haveTax;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getTaxes() {
        return taxes;
    }
    
    public void setTaxes(Double taxes) {
        this.taxes = taxes;
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

    @Override
    public String toString(){
        String data =  "\nPayment Check: {";
        data += "\nEmployee: " + getEmployee();
        data += "\n\tPayment Value: " + getPaymentValue();
        data += "\n\tTaxes: " + getTaxes();
        data += "\n\tHave Tax: " + isHaveTax();
        data +=  "\n\tDate: " + getDate();
        data += "\n}\n";

        return data;
    }
}
