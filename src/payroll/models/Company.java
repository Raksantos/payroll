package models;
import java.util.ArrayList;

import models.services.payment.PaymentList;
import models.services.payment.PaymentSchedule;

import java.io.Serializable;

public class Company implements Serializable{
    private ArrayList<Employee> employees;

    private ArrayList<PaymentList> paymentLists;

    private ArrayList<PaymentSchedule> paymentSchedules;     

    public Company(){
        this.employees = new ArrayList<Employee>();
        this.paymentLists = new ArrayList<PaymentList>();
        this.paymentSchedules = new ArrayList<PaymentSchedule>();
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

    public ArrayList<PaymentSchedule> getPaymentSchedules() {
        return paymentSchedules;
    }

    public void setPaymentSchedules(ArrayList<PaymentSchedule> paymentSchedules) {
        this.paymentSchedules = paymentSchedules;
    }
}
