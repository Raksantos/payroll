package models;

import java.util.UUID;
import models.services.payment.PaymentData;

public class Employee {
    private UUID id;
    private String name;
    private String address;
    private Double salary;
    private PaymentData paymentData;
    private Syndicate employeeSyndicate;
    
    public Employee(){

    }

    public Employee(String name, String address, Double salary, PaymentData paymentData){
        this.id = UUID.randomUUID();
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.paymentData = paymentData;
        this.employeeSyndicate = null;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Syndicate getEmployeeSyndicate() {
        return employeeSyndicate;
    }

    public void setEmployeeSyndicate(Syndicate employeeSyndicate) {
        this.employeeSyndicate = employeeSyndicate;
    }

    public PaymentData getPaymentData() {
        return paymentData;
    }

    public void setPaymentData(PaymentData paymentData) {
        this.paymentData = paymentData;
    }

    @Override
    public String toString(){
        String data = "\n{\n\tUser id: " + getId();
        data += "\n\tName: " + getName();
        data += "\n\tAddress: " + getAddress();
        data += "\n\tSalary: " + getSalary();
        data += "\n\tPayment Data: {" + getPaymentData();

        if(this.employeeSyndicate.getIsAffiliated()){
            data += "\n\tSyndicate: { ";
            data += this.employeeSyndicate.toString();
            data += "\n\t}";
        }

        data += "\n}\n";
        return data;
    }
}
