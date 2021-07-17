package models;
import java.util.ArrayList;

import models.services.payment.PaymentList;

public class Company {
    private ArrayList<Employee> employees;

    private ArrayList<PaymentList> paymentLists;

    public Company(){
        this.employees = new ArrayList<Employee>();
        this.paymentLists = new ArrayList<PaymentList>();
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<PaymentList> getPaymentLists() {
        return paymentLists;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public void setPaymentLists(ArrayList<PaymentList> paymentLists) {
        this.paymentLists = paymentLists;
    }
}
